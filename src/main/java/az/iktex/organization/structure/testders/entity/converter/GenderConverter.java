package az.iktex.organization.structure.testders.entity.converter;

import az.iktex.organization.structure.testders.enums.Gender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }
        return gender.getId();
    }

    @Override
    public Gender convertToEntityAttribute(Integer id) {
        if (id == null) {
            return null;
        }
        return Gender.fromId(id);
    }
}