package org.example.res;

import org.example.models.Car;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.HashMap;
import java.util.Map;

@Path("cars")
public class CarResource {
    private static Map<Integer, Car> carsMap = new HashMap<>();
    private static int nextCarId = 1;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCars() {
        return Response.ok(carsMap.values()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarById(@PathParam("id") int id) {
        Car car = carsMap.get(id);
        if (car != null) {
            return Response.ok(car).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCar(Car car) {
        if (carsMap.containsKey(car.getId())) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        car.setId(nextCarId);
        carsMap.put(nextCarId, car);
        nextCarId++;
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCar(@PathParam("id") int id, Car updatedCar) {
        if (updatedCar == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Car car = carsMap.get(id);
        if (car != null) {
            car.setBrand(updatedCar.getBrand());
            car.setModel(updatedCar.getModel());
            car.setHorsePower(updatedCar.getHorsePower());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCar(@PathParam("id") int id) {
        Car removedCar = carsMap.remove(id);
        if (removedCar != null) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
