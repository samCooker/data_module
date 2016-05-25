package springcommon.jpa;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by Cookie on 2016/3/26.
 */
@MappedSuperclass
public abstract class IdEntity<ID extends Serializable> implements Serializable {

    private static final long serialVersionUID = -8576323123959946826L;

    @Id
    @GeneratedValue
    protected ID              id;

    @Transient
    @JsonIgnore
    public boolean isNew() {
        return null == getId();
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof IdEntity) && (id != null)) {
            return id.equals(((IdEntity) obj).id);
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return super.hashCode();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
