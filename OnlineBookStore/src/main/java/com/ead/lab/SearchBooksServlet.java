package com.ead.lab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;

@RequestMapping("/searchBooks")
public class SearchBooksServlet {
    private static final String query = "select * from books where title like ?";

    @Setter
    private DBConnectionManager db;

    @GetMapping
    @ResponseBody
    public String searchBooks(@RequestParam("title") String title) {
        StringBuilder htmlResponse = new StringBuilder();
        db.openConnection();
        Connection connection = db.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + title + "%");
            ResultSet rs = ps.executeQuery();
            ArrayList<Book> books = new ArrayList<Book>();
            while (rs.next()) {
                books.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getLong(4)));
            }

            htmlResponse.append("<html>");
            htmlResponse.append("<head>");
            htmlResponse.append("<title>Search Results</title>");
            htmlResponse.append("<style>");
            htmlResponse.append("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f9f9f9; }");
            htmlResponse.append("h2 { color: #333; text-align: center; }");
            htmlResponse.append(".task-table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            htmlResponse.append(".task-table th, .task-table td { border: 1px solid #ccc; padding: 10px; text-align: left; }");
            htmlResponse.append(".task-table th { background-color: #007bff; color: #fff; }");
            htmlResponse.append(".task-table tr:nth-child(even) { background-color: #f2f2f2; }");
            htmlResponse.append("</style>");
            htmlResponse.append("</head>");
            htmlResponse.append("<body>");
            htmlResponse.append("<h2>Showing search results for: '").append(title).append("'</h2>");
            htmlResponse.append("<table class='task-table'>");
            htmlResponse.append("<tr>");
            htmlResponse.append("<th>Book ID</th>");
            htmlResponse.append("<th>Title</th>");
            htmlResponse.append("<th>Author</th>");
            htmlResponse.append("<th>Price</th>");
            htmlResponse.append("</tr>");
            for (Book book : books) {
                htmlResponse.append("<tr>");
                htmlResponse.append("<td>").append(book.id).append("</td>");
                htmlResponse.append("<td>").append(book.title).append("</td>");
                htmlResponse.append("<td>").append(book.author).append("</td>");
                htmlResponse.append("<td>").append(book.price).append("</td>");
                htmlResponse.append("</tr>");
            }
            htmlResponse.append("</table>");
            htmlResponse.append("</body>");
            htmlResponse.append("</html>");

        } catch (SQLException se) {
            se.printStackTrace();
            htmlResponse.append("<h1>").append(se.getMessage()).append("</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            htmlResponse.append("<h1>").append(e.getMessage()).append("</h1>");
        }

        htmlResponse.append("</body></html>");
        db.closeConnection();
        return htmlResponse.toString();
    }
}
