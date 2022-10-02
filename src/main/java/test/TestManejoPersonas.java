package test;

import domain.Persona;
import domain.PersonaDAO;
import java.util.List;

public class TestManejoPersonas {

    public static void main(String[] args) {
        PersonaDAO personaDao = new PersonaDAO();

//insertando objeto de tipo persona
        //  Persona personaNueva = new Persona("juan", "Vargas", "micorreo123", 45854);
        // personaDao.insertar(personaNueva);
        
        //modificar persona existente
        //Persona personaModificar = new Persona(4, "jorge", "castro", "no tengo", 554555);
       // personaDao.editar(personaModificar);
       
       
       //eliminar persona
       Persona eliminarPerona = new Persona(5);
       personaDao.eliminar(eliminarPerona);
       
        List<Persona> personas = personaDao.seleccionar();
        for (Persona persona : personas) {
            System.out.println("persona" + persona);

        }


    }
}
