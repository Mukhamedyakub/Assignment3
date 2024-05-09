package Part_1_2;

import java.util.Objects;

/**
 * Represents a testing class that will serve as keys in the hash table.
 */
class MyTestingClass {
    private int id;
    private String name;

    /**
     * Constructor for MyTestingClass.
     *
     * @param id the unique identifier
     * @param name the name associated with the id
     */
    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Custom hash code method for MyTestingClass.
     * This method ensures a uniform distribution of hash codes.
     *
     * @return the hash code
     */
//    @Override
//    public int hashCode() {
//        int hash = 17;
//        hash = 31 * hash + id;
//        hash = 31 * hash + (name != null ? name.hashCode() : 0);
//        return hash;
//    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + id; //
        hash ^= (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyTestingClass that = (MyTestingClass) obj;
        return id == that.id && (Objects.equals(name, that.name));
    }
}




