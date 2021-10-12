package pl.pg.eti.jee.bestbet.user.servlet;

import pl.pg.eti.jee.bestbet.servlet.ServletUtility;
import pl.pg.eti.jee.bestbet.user.dto.GetUserResponse;
import pl.pg.eti.jee.bestbet.user.dto.GetUsersResponse;
import pl.pg.eti.jee.bestbet.user.entity.User;
import pl.pg.eti.jee.bestbet.user.service.UserService;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = UserServlet.Paths.USERS + "/*")
public class UserServlet extends HttpServlet {

    /**
     * Service for user entity operations.
     */
    private UserService service;

    @Inject
    public UserServlet(UserService service) {
        this.service = service;
    }


    public static class Paths {
        public static final String USERS = "/api/users";
    }
    public static class Patterns {
        /**
         * All users.
         */
        public static final String USERS = "^/?$";

        /**
         * Specified user.
         */
        public static final String USER = "^/[A-Z,a-z]+/?$";

    }

    /**
     * JSON-B mapping object. According to open liberty documentation creating those is expensive. The JSON-B is only
     * one of many solutions. JSON strings can be build by hand {@link StringBuilder} or with JSON-P API. Both JSON-B
     * and JSON-P are part of Jakarta EE whereas JSON-B is newer standard.
     */
    private final Jsonb jsonb = JsonbBuilder.create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = ServletUtility.parseRequestPath(request);
        String servletPath = request.getServletPath();

        if(Paths.USERS.equals(servletPath)){
            if (path.matches(Patterns.USER)) {
                getUser(request, response);
                return;
            } else if (path.matches(Patterns.USERS)) {
                getUsers(request, response);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);

    }

    private void getUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.findAll().forEach(user -> {
            try {
                response.getWriter().write(jsonb.toJson(GetUserResponse.entityToDtoMapper().apply(user)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void getUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String login = ServletUtility.parseRequestPath(request).replaceAll("/", "");
        Optional<User> user = service.find(login);
        if (user.isPresent()) {
            response.getWriter()
                    .write(jsonb.toJson(GetUserResponse.entityToDtoMapper().apply(user.get())));
            return;
        }
        response.getWriter().write("{}");//Empty JSON object.
    }


}
