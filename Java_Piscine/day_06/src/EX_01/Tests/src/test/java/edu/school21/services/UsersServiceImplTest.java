package edu.school21.services;

import edu.school21.exeptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class UsersServiceImplTest extends Assertions {
    private EmbeddedDatabase database;
    private UsersServiceImpl usersService;


    UsersServiceImpl moc = mock(UsersServiceImpl.class);

    @BeforeEach
    public void init(){
        database = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();

        try {
            usersService = new UsersServiceImpl(database.getConnection());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByLogin() {
        User user = new User(0L, "login123", "password123", true);
        moc.findByLogin("darkStar");
        verify(moc).findByLogin("darkStar");

        when(moc.findByLogin("darkStar")).thenReturn(user);
        assertEquals(user, moc.findByLogin("darkStar"));
    }

    @Test
    public void testUpdate() {
        User user = new User(1L, "adminNew", "admin123New", true);


        ArgumentCaptor<User> valueCaptor = ArgumentCaptor.forClass(User.class);
        doNothing().when(moc).update(valueCaptor.capture());
        moc.update(user);

        assertEquals(user, valueCaptor.getValue());
    }

    @Test
    public void testAuthenticate() {
        moc.authenticate("login123", "password123");
        verify(moc).authenticate("login123", "password123");
    }

    @Test
    public void testPassword_False(){
        moc.authenticate("login123", "passwordWrong");
        verify(moc).authenticate("login123", "passwordWrong");

        when(moc.authenticate("login123", "passwordWrong")).thenReturn(false);
        assertFalse(moc.authenticate("login123", "passwordWrong"));
    }

    @Test
    public void checkAuthenticateByLoginException() {
        assertThrows(EntityNotFoundException.class, () -> usersService.authenticate("temp", "temp"));
    }

    @Test
    public void checkAlreadyAuthenticatedException(){
        assertThrows(AlreadyAuthenticatedException.class, () -> usersService.authenticate("admin", "admin123"));
    }


    @AfterEach
    public void end() {
        database.shutdown();
    }


}
