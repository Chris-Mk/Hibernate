package app.mBeans;

import lombok.NoArgsConstructor;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
@NoArgsConstructor
public class UserLogoutManagedBean {

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        ((HttpSession) externalContext.getSession(false))
                .invalidate();

        externalContext.redirect("/");
    }
}
