package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import sample.dataaccess.EmployeeDAO;
import sample.dataaccess.EmployeeDAOMySQL;
import sample.model.Employee;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Controller {

    @FXML
    private BarChart<String, Integer> barChart1;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private void initialize() {

        EmployeeDAO eDAO = new EmployeeDAOMySQL();
        List<Employee> listaE = eDAO.findAll();

        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();

        ObservableList<String> olmonths = FXCollections.observableArrayList();
        olmonths.addAll(months);

        xAxis.setCategories(olmonths);

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        for (int i = 0; i < months.length; i++) {
            int cuenta = 0;
            for (Employee e:
                 listaE) {
                if ((e.getDob().getMonth()) == i) {
                    cuenta++;
                }
            }
            series.getData().add(new XYChart.Data<>(months[i], cuenta));
        }

        barChart1.getData().add(series);

    }

}
