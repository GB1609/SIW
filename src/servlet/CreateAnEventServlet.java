package servlet;

import java.awt.Event;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.DaoFactory;
import core.DataSource;
import dao.CategoryDao;
import dao.EventsDao;
import dao.InformationDao;
import dao.PartecipantsDao;
import dao.TicketDao;
import tables.Events;
import tables.Information;
import tables.Ticket;

@WebServlet("/CreateAnEventServlet")
public class CreateAnEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected DataSource datSource;
	protected DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String categoria = request.getParameter("categoria");
		String partecipante = request.getParameter("partecipanti");
		String citta = request.getParameter("citta");
		String luogo = request.getParameter("luogo");
		String bigl = request.getParameter("tipoBiglietti");
		String numeroB = request.getParameter("numeroBiglietti");
		String costoB = request.getParameter("costoBiglietti");
		String descrizione = request.getParameter("description");
		String date = request.getParameter("dataEvento");
		String url = request.getParameter("immagine");
		String messaggio = "";
		try {
			Integer.parseInt(numeroB);
			Integer.parseInt(costoB);
			EventsDao ed = daoFactory.getEventoDao();
			TicketDao td = daoFactory.getBigliettoDao();
			CategoryDao cd = daoFactory.getCategoriaDao();
			InformationDao id = daoFactory.getInformationDao();
			int categoryCode = cd.returnCode(categoria);
			Information i = new Information(LocalDate.parse(date), luogo, descrizione, citta, nome, url);
			id.save(i);
			Information i2 = ed.getInfoByName(nome);
			int infId = i2.getInformationId();
			Events e = new Events(-1, "", request.getParameter("nome"), categoryCode, infId);
			ed.save(e);
			List<Events> uffa = ed.searchByName(nome);
			for (int index = 0; index < Integer.parseInt(numeroB); index++) {
				td.save(new Ticket(-1, uffa.get(0).getEventcode(), Integer.parseInt(costoB), bigl, false));
			}
		} catch (Exception e) {
			
				messaggio = "Errore nei parametri inseriti";
		}
		if (messaggio.equals(""))
			messaggio = "Creazione evento completata";

		String s = new Gson().toJson(messaggio);
		response.setContentType("application/json");
		response.getWriter().write(s);

	}

}
