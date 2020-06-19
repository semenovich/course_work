package service.action.orderactions;

import bean.Order;
import controller.filter.StatusCheckFilter;
import dao.DAOFactory;
import exception.PersistentException;
import org.apache.log4j.Logger;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCheckInFileService implements Action {
    private static final Logger logger = Logger.getLogger(SaveCheckInFileService.class.getName());
    private File file = new File("B:\\Учёба\\ПрогСП\\project\\data\\check.txt");

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        Order order = DAOFactory.getInstance().getOrderDAO().read(Integer.parseInt(req.getParameter("order_id")));
        try {
            logger.debug("Writing text to "+file.getName()+" file...");
            FileWriter fileWriter = new FileWriter(file,true);
            fileWriter.write(order.toString());
            fileWriter.close();
            logger.debug("Writing was successful");
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
        }
        new ReadAllOrderService().execute(req,resp);
    }
}
