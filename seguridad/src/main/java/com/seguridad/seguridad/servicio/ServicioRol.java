package com.seguridad.seguridad.servicio;

import java.util.List;
import org.springframework.stereotype.Service; 
import com.seguridad.seguridad.modelo.Rol;
import com.seguridad.seguridad.repositorio.RepositorioRol;

@Service
public class ServicioRol {

    //Declaramos una variable dentro del servicio para poder comunicarnos con la base de datos.
    //private: significa que solo se puede usar dentro de esta misma clase, nadie de afuera puede modificarla por error.
    //final: indica que una vez que se le asigna el repositorio al iniciar la aplicación, nunca más
    //va a cambiar ni ser reemplazado. Esto garantiza mayor seguridad y estabilidad en tu código.
    //RepositorioRol: es el tipo de dato, es la interfaz que creamos para hablar con la base de datos 
    //la que tiene el @Query para ejecutar el procedimiento de almacenado.
    //rolRepositorio: es el nombre que le dimos a esta variable para usarla cada vez que necesitemos consultar,
    //guardar o eliminar datos.

    private final RepositorioRol rolRepositorio;

    // Constructor Único
    //El Constructor se ejecuta una sola vez cuando Spring Boot arranca y crea el servicio. Al poner RepositorioRol rolRepositorio entre los paréntesis, le decimos a Spring que cuando cree el servicio, busque el repositorio de roles que ya tiene guardado en tu memoria y lo pase aquí"

    //La Asignación: toma ese repositorio que Spring le entregó y lo guardar en la variable private final RepositorioRol rolRepositorio que definimos arriba, para que podamos usarla en cualquier momento dentro de los diferentes métodos.

    public ServicioRol(RepositorioRol rolRepositorio) {
        this.rolRepositorio = rolRepositorio;
    }


    //Este método es público porque puede ser llamado desde otras clases, como nuestro controlador y que va a devolver una lista de objetos de tipo Rol.
    //listarRoles():es el nombre del método que creamos. 
    //return rolRepositorio.rol_listar()es la instrucción que ejecuta la consulta. Toma la variable del repositorio (rolRepositorio) que guardamos en el paso anterior y ejecuta el método rol_listar() que está conectado al procedimiento almacenado en la base de datos mediante la anotación @Query. Todo lo que devuelva la base de datos se empaqueta y se regresa hacia el controlador.
    
    public List<Rol> listarRoles(){
        return rolRepositorio.rol_listar();
    }  
    
    // Método para agregar un rol usando nuestro procedimiento de SQL
    public void agregarRol(Rol rol) {
        // Usamos getters para obtener los valores
            rolRepositorio.rol_agregar(
            rol.getNom_rol(),
            rol.getDes_rol(),
            rol.getEst_rol()
        );
    } 
    //Metodo para buscar un Rol por su codigo
    public Rol buscarRol(int cod_rol) {
        return rolRepositorio.rol_buscar(cod_rol);
    }

    // Metodo para modificar un rol por su ID usando nuestro procedimiento de SQL
    public void editarRol(Rol rol) {
        // Usamos getters para obtener los valores actuales del rol
        rolRepositorio.rol_editar(
            rol.getCod_rol(),
            rol.getNom_rol(),
            rol.getDes_rol(),
            rol.getEst_rol()
        );
    }

    // Método para eliminar un rol usando nuestro procedimineto de SQL
    public boolean eliminarRol(int cod_rol) {
        Rol rol = rolRepositorio.rol_buscar(cod_rol);
        if (rol != null) {
            rolRepositorio.rol_eliminar(cod_rol);
            return true;
    }
    return false;
}


}