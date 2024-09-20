/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Servlet;

import Entity.Chapters;
import Entity.Comics;
import Session.ChaptersFacadeLocal;
import Session.ComicsFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
public class reader extends HttpServlet {

    @EJB
    private ChaptersFacadeLocal chaptersFacade;

    @EJB
    private ComicsFacadeLocal comicsFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            List<Chapters> chNews = chaptersFacade.findAllNew();
            request.setAttribute("chNews", chNews);
            Comics cRand = comicsFacade.find(1);
            request.setAttribute("cRand", cRand);
            List<Comics> cNews = comicsFacade.findAllNew();
            request.setAttribute("cNews", cNews);
            List<Comics> cHots = comicsFacade.findAllHot();
            request.setAttribute("cHots", cHots);

            Integer cCount = comicsFacade.count();
            request.setAttribute("cCount", cCount);
            Integer chCount = chaptersFacade.count();
            request.setAttribute("chCount", chCount);
            RequestDispatcher rd = request.getRequestDispatcher("/reader.jsp");
            rd.forward(request, response);

//            for (Chapters chNew : chNews) {
//                out.print(chNew);
//            }
        } catch (Exception e) {
            out.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
