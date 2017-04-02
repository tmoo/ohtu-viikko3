package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;

/**
 *
 * @author tuomo
 */
public class FileUserDAO implements UserDao {

    private final String tiedosto;

    public FileUserDAO(String tiedosto) {
        this.tiedosto = tiedosto;
    }

    @Override
    public List<User> listAll() {
        try {
            Scanner sc = new Scanner(new File(tiedosto));
            List<User> kayttajat = new ArrayList<>();
            while (sc.hasNext()) {
                String rivi = sc.nextLine();
                String[] jaettuRivi = rivi.split(",");
                String knimi = jaettuRivi[0];
                String ssana = jaettuRivi[1];
                kayttajat.add(new User(knimi, ssana));
            }
            return kayttajat;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User findByName(String name) {
        List<User> kayttajat = listAll();
        for (User user : kayttajat) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        try (FileWriter fw = new FileWriter(tiedosto, true)) {
            fw.append(user.getUsername() + "," + user.getPassword() + "\n");
        } catch (IOException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
