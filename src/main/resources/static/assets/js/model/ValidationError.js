export default class ValidationError extends Error {

    constructor(message, campo = null){
        super(message);
        this.campo = campo;
    }
}