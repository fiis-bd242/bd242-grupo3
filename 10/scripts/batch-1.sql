-- Busqueda de los reportes vencidos por parte de supervisión.
CREATE OR REPLACE FUNCTION actualizar_reportes_vencidos()
    RETURNS VOID AS $$
    DECLARE
        reporte_cursor CURSOR FOR
            SELECT Id_reporte, id_jefe
            FROM Reportes
            WHERE Fecha_reporte < CURRENT_DATE + 1
            AND id_estado_reporte = 1;
            
        reporte_record RECORD;
        supervisor_cursor CURSOR FOR
            SELECT e.Id_empleado
            FROM Empleado e
            JOIN Cargo_Empleado ce ON e.Id_empleado = ce.Id_empleado
            WHERE ce.id_cargo = 3;
            
        supervisor_record RECORD;
    BEGIN
        OPEN reporte_cursor;

        LOOP
            FETCH reporte_cursor INTO reporte_record;
            EXIT WHEN NOT FOUND;  

            OPEN supervisor_cursor;
            
            LOOP
                FETCH supervisor_cursor INTO supervisor_record;
                EXIT WHEN NOT FOUND; 
                INSERT INTO Notificaciones (
                    id_notificacion, Asunto, mensaje, fecha_notificacion, 
                    id_remitente, id_destinatario, Id_reporte, id_tipo
                )
                VALUES (
                    DEFAULT, 
                    'Reporte vencido',
                    'El reporte con ID ' || reporte_record.Id_reporte || ' ha sido marcado como vencido.',
                    NOW(),
                    reporte_record.id_jefe,  
                    supervisor_record.Id_empleado,  
                    reporte_record.Id_reporte,
                    1  
                );

            END LOOP;

            CLOSE supervisor_cursor;

            UPDATE Reportes
            SET id_estado_reporte = 4
            WHERE Id_reporte = reporte_record.Id_reporte;

        END LOOP;

        CLOSE reporte_cursor;
    END;
    $$ LANGUAGE plpgsql;


    select actualizar_reportes_vencidos();