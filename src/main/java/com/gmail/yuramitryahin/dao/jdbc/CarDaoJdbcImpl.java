package com.gmail.yuramitryahin.dao.jdbc;

import com.gmail.yuramitryahin.dao.CarDao;
import com.gmail.yuramitryahin.exception.DataProcessingException;
import com.gmail.yuramitryahin.lib.Dao;
import com.gmail.yuramitryahin.modal.Driver;
import com.gmail.yuramitryahin.modal.Car;
import com.gmail.yuramitryahin.modal.Manufacturer;
import com.gmail.yuramitryahin.util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class CarDaoJdbcImpl implements CarDao {
    @Override
    public Car create(Car car) {
        String query = "INSERT INTO cars (model, manufacturer_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement =
                         connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setLong(2, car.getManufacturer().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                car.setId(resultSet.getObject("id", Long.class));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add car " + car, e);
        }
        return car;
    }

    @Override
    public Optional<Car> get(Long id) {
        String query = "SELECT c.id AS car_id, c.model, m.id AS manufacturer_id, m.name, m.country"
                + "FROM cars c "
                + "INNER JOIN manufacturer m ON c.manufacturer_id = m.id"
                + "WHERE c.id = ? AND c.deleted = FALSE;";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return Optional.ofNullable(getCar(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't find car by id " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT c.id AS car_id, c.model, m.id AS manufacturer_id, m.name, m.country"
                + "FROM cars c "
                + "INNER JOIN manufacturer m ON c.manufacturer_id = m.id"
                + "WHERE c.deleted = FALSE;";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cars.add(getCar(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't find any cars", e);
        }
        return cars;
    }

    @Override
    public Car update(Car car) {
        String delete = "DELETE FROM car_drivers WHERE car_id = ?";
        String insert = "INSERT INTO cars_drivers (driver_id, car_id) VALUES (?, ?)";
        String update = "UPDATE cars SET model=?, manufacturer_id=? WHERE id=? AND deleted=false";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement deleteStatement = connection.prepareStatement(delete);
                 PreparedStatement insertStatement = connection.prepareStatement(insert);
                 PreparedStatement updateStatement = connection.prepareStatement(update)) {
            deleteStatement.setLong(1, car.getId());
            updateStatement.setString(1, car.getModel());
            updateStatement.setLong(2, car.getManufacturer().getId());
            updateStatement.setLong(3, car.getId());
            deleteStatement.executeUpdate();
            updateStatement.executeUpdate();
            for (Driver driver : car.getDrivers()) {
                insertStatement.setLong(1, driver.getId());
                insertStatement.setLong(2, car.getId());
                insertStatement.executeUpdate();
            }
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update car " + car, e);
        }

    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE cars SET deleted=true WHERE id=?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete car by id " + id, e);
        }
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        String query = "SELECT cd.car_id, c.model, c.manufacturer_id "
                + "FROM car_drivers cd "
                + "INNER JOIN cars c"
                + "ON c.id = cd.car_id "
                + "WHERE cd.driver_id = ? AND c.deleted = FALSE";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, driverId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cars.add(getCar(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete car by driver id " + driverId, e);
        }
        return cars;
    }

    private Car getCar(ResultSet resultSet) throws SQLException {
        Long driverId = resultSet.getObject("id", Long.class);
        String model = resultSet.getString("model");
        Manufacturer manufacturer = getManufacturer(resultSet);
        Car car = new Car(driverId, model, manufacturer);
        car.setDrivers(getListDrivers(driverId));
        return car;
    }

    private List<Driver> getListDrivers(Long carId) {
        String query = "SELECT c.driver_id, d.name, d.liсense_number "
                + "FROM car_drivers c INNER JOIN drivers d ON d.id = c.driver_id "
                + "WHERE c.car_id = ? AND d.deleted = FALSE";
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, carId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                drivers.add(getDriver(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't find driver by car id " + carId, e);
        }
        return drivers;
    }

    private Manufacturer getManufacturer(ResultSet resultSet) throws SQLException {
        Long manufacturerId = resultSet.getObject("id", Long.class);
        String name = resultSet.getString("name");
        String country = resultSet.getString("country");
        return new Manufacturer(manufacturerId, name, country);
    }

    private Driver getDriver(ResultSet resultSet) throws SQLException {
        Long driverId = resultSet.getObject("id", Long.class);
        String name = resultSet.getString("name");
        String licenseNumber = resultSet.getString("license_number");
        return new Driver(driverId, name, licenseNumber);
    }
}
