/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chiquimula;
import java.util.*;
/**
 *
 * @author Fernanda
 */
public class Chiquimula {


    private static final int INFINITO = Integer.MAX_VALUE;

    private static class Municipio {
        String nombre;
        Map<Municipio, Integer> conexiones;
        int distancia;

        Municipio(String nombre) {
            this.nombre = nombre;
            conexiones = new HashMap<>();
            distancia = INFINITO;
        }
    }

    public static void main(String[] args) {
        Map<String, Municipio> municipios = crearMunicipios();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el lugar de origen: ");
        String origen = scanner.nextLine();

        // Verificar si el lugar de origen es válido
        if (municipios.containsKey(origen)) {
            // Encontrar la ruta más corta desde el origen hacia todos los destinos
            Map<Municipio, List<Municipio>> rutas = encontrarRutaMasCorta(municipios, origen);

            // Mostrar las rutas más cortas para cada destino
            for (Municipio destino : rutas.keySet()) {
                List<Municipio> ruta = rutas.get(destino);
                System.out.println("Ruta más corta desde " + origen + " hasta " + destino.nombre + ":");
                if (ruta != null) {
                    for (int i = 0; i < ruta.size() - 1; i++) {
                        Municipio municipio = ruta.get(i);
                        System.out.println(municipio.nombre);
                    }
                    System.out.println("Distancia total: " + obtenerDistanciaTotal(ruta) + " km");
                } else {
                    System.out.println("No se encontró una ruta hasta " + destino.nombre);
                }
                System.out.println();
            }
        } else {
            System.out.println("El lugar de origen no existe. Por favor, ingrese un lugar válido.");
        }

        scanner.close();
    }

    private static Map<String, Municipio> crearMunicipios() {
        Map<String, Municipio> municipios = new HashMap<>();

        Municipio chiquimula = new Municipio("Chiquimula");
        Municipio esquipulas = new Municipio("Esquipulas");
        Municipio ipala = new Municipio("Ipala");
        Municipio jocotan = new Municipio("Jocotán");
        Municipio olopa = new Municipio("Olopa");
        Municipio camotan = new Municipio("Camotán");
        Municipio sanJose = new Municipio("San José");
        Municipio concepcion = new Municipio("Concepcion Minas");
        Municipio quetzaltepeque = new Municipio("Quetzaltepeque");
        Municipio sanjacinto = new Municipio("SanJacinto");
        Municipio sanjuanlaermita= new Municipio("San Juan la Ermita");

        municipios.put("Chiquimula", chiquimula);
        municipios.put("Esquipulas", esquipulas);
        municipios.put("Ipala", ipala);
        municipios.put("Jocotán", jocotan);
        municipios.put("Olopa", olopa);
        municipios.put("Camotán", camotan);
        municipios.put("San José", sanJose);
        municipios.put("Concepcion Minas", concepcion);
        municipios.put("Quetzaltepeque", quetzaltepeque);
        municipios.put("SanJacinto", sanjacinto);
        municipios.put("San Juan la Ermita", sanjuanlaermita);

        // Conexiones desde Ipala
        ipala.conexiones.put(sanJose, 18);
        ipala.conexiones.put(concepcion , 31);
      //  ipala.conexiones.put(sanjacinto, 43);

        // Conexiones desde San José
        sanJose.conexiones.put(chiquimula, 11);
        sanJose.conexiones.put(esquipulas, 60);
        sanJose.conexiones.put(quetzaltepeque, 34);
        sanJose.conexiones.put(sanjacinto, 23);
        sanJose.conexiones.put(olopa, 44);
        sanJose.conexiones.put(ipala, 18);
        sanJose.conexiones.put(concepcion, 57);

        // Conexiones desde Chiquimula
     //  chiquimula.conexiones.put(ipala, 29);
        chiquimula.conexiones.put(jocotan, 30);
        chiquimula.conexiones.put(sanJose,11);
          chiquimula.conexiones.put(sanjuanlaermita,21);
          chiquimula.conexiones.put(esquipulas,59);
          chiquimula.conexiones.put(quetzaltepeque, 30);
          chiquimula.conexiones.put(sanjacinto,20);
          chiquimula.conexiones.put(olopa,41);
          chiquimula.conexiones.put(concepcion,47);

        // Conexiones desde Jocotán
        jocotan.conexiones.put(camotan, 2);
       jocotan.conexiones.put(olopa, 24);
         jocotan.conexiones.put(sanjuanlaermita, 8);
        
        // Conexiones desde Olopa
        olopa.conexiones.put(camotan, 25);
        olopa.conexiones.put(quetzaltepeque, 20);
        olopa.conexiones.put(sanjuanlaermita, 22);
        olopa.conexiones.put(sanjacinto, 41);
        olopa.conexiones.put(jocotan, 24);
        
        // conexion desde san jacinto
        sanjacinto.conexiones.put(chiquimula, 24);
         sanjacinto.conexiones.put(sanJose, 23);
         sanjacinto.conexiones.put(quetzaltepeque, 19);
            sanjacinto.conexiones.put(sanjuanlaermita, 25);
      
        // Conexiones desde San Juan la Ermita
        sanjuanlaermita.conexiones.put(jocotan, 8);
        sanjuanlaermita.conexiones.put(camotan, 8);
        sanjuanlaermita.conexiones.put(chiquimula,21);
        sanjuanlaermita.conexiones.put(olopa,22);
        sanjuanlaermita.conexiones.put(sanJose,25);
        sanjuanlaermita.conexiones.put(quetzaltepeque,32);
        sanjuanlaermita.conexiones.put(sanjacinto,25);
         
        //conexiones desde quetzaltepeque
        
        quetzaltepeque.conexiones.put(chiquimula,31);
        quetzaltepeque.conexiones.put(sanJose,34);
        quetzaltepeque.conexiones.put(concepcion,17);
        quetzaltepeque.conexiones.put(olopa,20);
        quetzaltepeque.conexiones.put(sanjacinto,19);
        quetzaltepeque.conexiones.put(sanjuanlaermita,32);
        quetzaltepeque.conexiones.put(esquipulas,27);
        
        
        //conexiones desde Concepcion las minas
        
        concepcion.conexiones.put(quetzaltepeque,17);
         concepcion.conexiones.put(ipala,31);
          concepcion.conexiones.put(esquipulas,22);
         
           // conexines desde Camotan
           camotan.conexiones.put(olopa,25);
            camotan.conexiones.put(sanjuanlaermita,8); 
            camotan.conexiones.put(quetzaltepeque,40); 
            
           
           // conexiones desde Esquipulas
           
           esquipulas.conexiones.put(quetzaltepeque, 27);
           esquipulas.conexiones.put(concepcion, 22);
           
           
         
           
           
        return municipios;
    }

    private static Map<Municipio, List<Municipio>> encontrarRutaMasCorta(Map<String, Municipio> municipios, String origen) {
        PriorityQueue<Municipio> colaPrioridad = new PriorityQueue<>((m1, m2) -> m1.distancia - m2.distancia);
        Map<Municipio, Integer> distancias = new HashMap<>();
        Map<Municipio, List<Municipio>> rutas = new HashMap<>();
        Map<Municipio, Municipio> padres = new HashMap<>();

        // Inicializar las distancias con valor infinito, excepto para el origen
        for (Municipio municipio : municipios.values()) {
            if (municipio.nombre.equals(origen)) {
                distancias.put(municipio, 0);
                municipio.distancia = 0;
            } else {
                distancias.put(municipio, INFINITO);
                municipio.distancia = INFINITO;
            }
            colaPrioridad.offer(municipio);
        }

        while (!colaPrioridad.isEmpty()) {
            Municipio actual = colaPrioridad.poll();

            for (Map.Entry<Municipio, Integer> conexion : actual.conexiones.entrySet()) {
                Municipio destino = conexion.getKey();
                int distanciaTotal = distancias.get(actual) + conexion.getValue();
                if (distanciaTotal < distancias.get(destino)) {
                    distancias.put(destino, distanciaTotal);
                    destino.distancia = distanciaTotal;
                    padres.put(destino, actual);
                    // Actualizar la prioridad en la cola de prioridad
                    colaPrioridad.remove(destino);
                    colaPrioridad.offer(destino);
                }
            }
        }

        // Construir las rutas más cortas para cada destino
        for (Municipio destino : municipios.values()) {
            if (!destino.nombre.equals(origen)) {
                List<Municipio> ruta = new ArrayList<>();
                Municipio m = destino;
                while (m != null) {
                    ruta.add(0, m);
                    m = padres.get(m);
                }
                rutas.put(destino, ruta);
            }
        }

        return rutas;
    }

    private static int obtenerDistanciaTotal(List<Municipio> ruta) {
        int distanciaTotal = 0;
        for (int i = 0; i < ruta.size() - 1; i++) {
            Municipio actual = ruta.get(i);
            Municipio siguiente = ruta.get(i + 1);
            distanciaTotal += actual.conexiones.get(siguiente);
        }
        return distanciaTotal;
    }
}
