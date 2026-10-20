package az.iktex.organization.structure.testders.enums;

public enum Gender {
    MALE(1),
    FEMALE(2);

    private final Integer id;

    Gender(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static Gender fromId(Integer id) {
        for (Gender gender : values()) {
            if (gender.id.equals(id)) {
                return gender;
            }
        }

        throw new IllegalArgumentException("Naməlum Gender id: " + id);
    }
}