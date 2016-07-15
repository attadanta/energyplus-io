package eu.dareed.eplus.parsers.idd.tokens;

public final class GroupRange {

    public final String groupName;
    public final int startLine;
    public final int endLine;

    public GroupRange(String groupName, int startLine, int endLine) {
        this.groupName = groupName;
        this.startLine = startLine;
        this.endLine = endLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupRange that = (GroupRange) o;

        return groupName.equals(that.groupName);

    }

    @Override
    public int hashCode() {
        return groupName.hashCode();
    }

    @Override
    public String toString() {
        return "GroupRange{" +
                "name='" + groupName + '\'' +
                ", start=" + startLine +
                ", end=" + endLine +
                '}';
    }
}
