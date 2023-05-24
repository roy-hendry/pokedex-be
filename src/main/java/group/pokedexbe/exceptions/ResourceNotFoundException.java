package group.pokedexbe.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Defines to Spring what type of error status to return
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    // Defining the name for the resource in this example the model is Pokemon
    private String resourceName;

    // Defines the field variable we want to show is not found like id (in model class)
    private String fieldName;

    // Define the value that cannot be found
    private long fieldValue;


    // Constructor for the class
    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {

        // This will show the exception in string format e.g. 'Pokémon not found with id: -10'
        super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));

        this.resourceName = resourceName;

        this.fieldName = fieldName;

        this.fieldValue = fieldValue;
    }

    // Getters because for some reason lombok isn't letting me use the annotation here
    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getFieldValue() {
        return fieldValue;
    }

}
