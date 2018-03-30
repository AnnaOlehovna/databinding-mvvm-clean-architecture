package anna.poddubnaya.data.database;

import android.arch.persistence.room.Dao;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

import anna.poddubnaya.data.entity.User;
import io.reactivex.Flowable;


@Dao
public interface UserDao {

    @Insert
    void insert(List<User> userList);

    @Query("SELECT * FROM User")
    Flowable<List<User>> getAll();

    //здесь id = потому что user переименовали колонку  @ColumnInfo(name = "id")
    @Query("SELECT * FROM User WHERE id = :id LIMIT 1")
    Flowable<List<User>> getById(String id);

    @Query("DELETE FROM User")
    void deleteAll();

}
