package servlet;

import java.io.IOException;
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
import dao.EventsDao;
import dao.InformationDao;
import dao.PartecipantsDao;
import dao.SubCategoryDao;
import dao.TicketDao;
import tables.Events;
import tables.Information;
import tables.Ticket;

@WebServlet("/CreateAnEventServlet")
public class CreateAnEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected DataSource datSource;
	protected DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] partecipanti = request.getParameterValues("partecipanti[]");
		String[] tipoBiglietti = request.getParameterValues("tipoBiglietti[]");
		String[] numeroBiglietti = request.getParameterValues("numeroBiglietti[]");
		String[] costoBiglietti = request.getParameterValues("costoBiglietti[]");

		String nome = request.getParameter("nome");
		String categoria = request.getParameter("categoria");
		String citta = request.getParameter("citta");
		String luogo = request.getParameter("luogo");
		String descrizione = request.getParameter("description");
		String date = request.getParameter("dataEvento");
		String url = request.getParameter("immagine");
		String messaggio = "";
		try {
			System.out.println("CIAO");
			PartecipantsDao pd = this.daoFactory.getPartecipantsDao();
			EventsDao ed = this.daoFactory.getEventsDao();
			TicketDao td = this.daoFactory.getTicketDao();
			this.daoFactory.getCategoryDao();
			InformationDao id = this.daoFactory.getInformationDao();
			SubCategoryDao scd = this.daoFactory.getSubCategoryDao();
			int categoryCode = scd.returnCode(categoria);
			Information i = new Information(LocalDate.parse(date), luogo, descrizione, citta, nome, url);
			id.save(i);
			Information i2 = ed.getInfoByName(nome);
			int infId = i2.getInformationId();
			Events e = new Events(-1, "", "vic", categoryCode, infId);
			ed.save(e);
			List<Events> uffa = ed.searchByName(nome);
			for (int j = 0; j < tipoBiglietti.length; j++)
				td.save(new Ticket(-1, uffa.get(0).getEventcode(), Integer.parseInt(costoBiglietti[j]),
						tipoBiglietti[j], true), Integer.parseInt(numeroBiglietti[j]));

			for (String element : partecipanti)
				ed.insertPartecipant(pd.getPartecipantCode(element), uffa.get(0).getEventcode());

		} catch (Exception e) {
			e.printStackTrace();

			messaggio = "Errore nei parametri inseriti";
		}
		if (messaggio.equals(""))
			messaggio = "Creazione evento completata";

		String s = new Gson().toJson(messaggio);
		System.out.println("CIAO2");
		response.setContentType("application/json");
		response.getWriter().write(s);

	}

}
