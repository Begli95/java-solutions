package expedition;

// выбрасывается при невозможности разместить экипаж из сломанной машины
class ExpeditionFailureException extends RuntimeException {
    public ExpeditionFailureException(String message) {
        super(message);
    }
}
