package com.hantsylabs.example.ee8.jsonb;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author hantsy
 */
public class PhoneNumber implements Serializable {

    public static enum Type {
        HOME, OFFICE;
    }

    private Type type;
    private String number;

    public PhoneNumber() {
    }

    public PhoneNumber(Type type, String number) {
        this.type = type;
        this.number = number;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.type);
        hash = 41 * hash + Objects.hashCode(this.number);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhoneNumber other = (PhoneNumber) obj;
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PhoneNumber{"
                + "type=" + type
                + ", number=" + number
                + '}';
    }

}
