import { defineStore } from "pinia";

export const useUserStore = defineStore({
    id: 'user',
    state: () => ({
        user : {
            isAuthenticated : false,
            first_name : null,
            last_name : null,
            email: null,
            role: null,
            id_cuenta: null,
            id_cliente: null,
            direccion: null
        }
    }),
    actions: {
        initStore() {
            console.log(localStorage);
            this.user.email = localStorage.getItem('user.email')
            this.user.last_name = localStorage.getItem('user.last_name') 
            this.user.role = localStorage.getItem('user.role')
            this.user.first_name = localStorage.getItem('user.first_name')
            this.user.id_cuenta = localStorage.getItem('user.id_cuenta')
            this.user.id_cliente = localStorage.getItem('user.id_cliente')
            this.user.direccion = localStorage.getItem('user.direccion')
            if (localStorage.getItem('user.last_name')){
                this.user.isAuthenticated = true 
            }

            console.log('Usuario inicializado:',this.user); 

        },
        login(data){
            console.log("The data");
            console.log(data);
            this.user.email = data.email
            this.user.first_name = data.first_name
            this.user.last_name = data.last_name 
            this.user.role = data.role
            this.user.id_cuenta = data.id_cuenta 
            this.user.id_cliente = data.id_cliente
            this.user.direccion = data.direccion
            localStorage.setItem('user.email',data.email)
            localStorage.setItem('user.first_name',data.first_name)
            localStorage.setItem('user.last_name',data.last_name)
            localStorage.setItem('user.role',data.role)
            localStorage.setItem('user.id_cuenta',data.id_cuenta)
            localStorage.setItem('user.id_cliente',data.id_cliente)
            localStorage.setItem('user.direccion',data.direccion)
            this.user.isAuthenticated = true
        },
        logout(){
            this.user.email = null
            this.user.first_name = null
            this.user.last_name = null 
            this.user.role = null
            this.id_cuenta = null
            this.id_cliente=  null
            this.user.isAuthenticated = false
            this.user.direccion = null
            localStorage.setItem('user.email','')
            localStorage.setItem('user.first_name','')
            localStorage.setItem('user.last_name','')
            localStorage.setItem('user.role','')
            localStorage.setItem('user.id_cuenta','')
            localStorage.setItem('user.id_cliente','')
            localStorage.setItem('user.direccion','')
        },
    }
})