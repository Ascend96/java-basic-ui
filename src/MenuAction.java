// Enum for GUI menu actions with labels to clean up code
public enum MenuAction {
    SHOW_DATE("Show Date"),
    LOG_TO_FILE("Log Text to File"),
    RANDOM_GREEN("Random Green Hue"),
    EXIT("Exit");

    private final String label;

    MenuAction(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
