import { defineStore } from 'pinia'

export const useToastStore = defineStore({
  id: 'toast',

  state: () => ({
    ms: 0,
    message: '',
    type: '',
    classes: '',
    isVisible: false
  }),

  actions: {
    showToast(ms, message, type, classes) {
      this.ms = parseInt(ms)
      this.message = message
      this.type = type
      this.classes = classes
      this.isVisible = true
      setTimeout(() => {
        this.classes += ' -translate-y-28'
      }, 10)

      setTimeout(() => {
        this.classes = this.classes.replace('-translate-y-28', '')
      }, this.ms - 500)

      setTimeout(() => {
        this.isVisible = false
      }, this.ms)
    }
  }
})