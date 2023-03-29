package com.example.backend.entities;

import com.fasterxml.jackson.annotation.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "sub_module")
@Table(schema = "public")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class SubModule  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "submodulename",unique = true)
    @NonNull
    private String subModuleName;

    @Column(name = "path",unique = true)
    @NonNull
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    @JsonBackReference
    private Module Module;

    @Column(name = "function",unique = true)
    @OneToMany(mappedBy = "subModule",orphanRemoval = true, cascade = {CascadeType.PERSIST ,CascadeType.MERGE,CascadeType.DETACH})
    @JsonManagedReference
    private List<Function> functions;


    public SubModule(Long id, @NonNull String subModuleName, @NonNull String path, com.example.backend.entities.Module module, List<Function> functions) {
        this.id = id;
        this.subModuleName = subModuleName;
        this.path = path;
        Module = module;
        this.functions = functions;
    }

    public SubModule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubModuleName() {
        return subModuleName;
    }

    public void setSubModuleName(String subModuleName) {
        this.subModuleName = subModuleName;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public com.example.backend.entities.Module getModule() {
        return Module;
    }

    public void setModule(com.example.backend.entities.Module module) {
        Module = module;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }
}
