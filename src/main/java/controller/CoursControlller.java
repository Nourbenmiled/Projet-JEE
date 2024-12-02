package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cours;
import model.CoursHome;

import java.io.IOException;
import java.util.List;

public class CoursControlller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CoursHome coursHome;

    public CoursControlller() {
        super();
        coursHome = new CoursHome();
        System.out.println("CoursControlller initialized!");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "add":
                    showAddForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteCours(request, response);
                    break;
                default:
                    listCours(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("CoursControlller?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            String nom = request.getParameter("nom");
            String description = request.getParameter("description");
            String coefParam = request.getParameter("coef");

            if (nom == null || description == null || coefParam == null) {
                throw new IllegalArgumentException("Tous les champs sont obligatoires.");
            }

            float coef = Float.parseFloat(coefParam);
            Cours cours = new Cours(nom, description, coef);

            if (idParam != null && !idParam.isEmpty()) {
                cours.setIdCours(Integer.parseInt(idParam));
            }

            coursHome.saveOrUpdate(cours);
            response.sendRedirect("CoursControlller?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors du traitement : " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/views/cours-form.jsp").forward(request, response);
        }
    }

    private void listCours(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cours> coursList = coursHome.findAll();
        request.setAttribute("coursList", coursList);
        request.getRequestDispatcher("WEB-INF/views/cours-list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cours", null);
        request.getRequestDispatcher("WEB-INF/views/cours-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Cours cours = coursHome.findById(id);
            if (cours != null) {
                request.setAttribute("cours", cours);
                request.getRequestDispatcher("WEB-INF/views/cours-form.jsp").forward(request, response);
            } else {
                response.sendRedirect("CoursControlller?action=list");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("CoursControlller?action=list");
        }
    }

    private void deleteCours(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Cours cours = coursHome.findById(id);
            if (cours != null) {
                coursHome.delete(cours);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        response.sendRedirect("CoursControlller?action=list");
    }
}
