package com.sumanth.pract.__12.service;

import com.sumanth.pract.__12.dto.Data;
import com.sumanth.pract.__12.dto.Postdto;
import com.sumanth.pract.__12.dto.Updatedto;
import com.sumanth.pract.__12.entity.User;
import com.sumanth.pract.__12.repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class Controller1sercice {
@Autowired
   Userrepo userrepo;
    public User signup(Postdto postdto) {
        User user= new User();
        user.setName(postdto.getName());
        user.setPassword(postdto.getPassword());
        user.setEmail(postdto.getEmail());

  return  userrepo.save(user);
    }

    public Boolean update(Updatedto updatedto) {

      Optional<User> user= userrepo.findById(updatedto.getId());
       if(user.isPresent()==true)
       {
          User user1= user.get();
          user1.setEmail(updatedto.getEmail());
          userrepo.save(user1);
          return true;
       }
       else {
           return false;
       }

    }
@Transactional
    public Object loginquery(Data data) {

        Optional<User> dbdata=userrepo.dbloginproc(data.getEmail(),data.getPassword());
        if(dbdata.isPresent()==true)
        {
            return dbdata.get();
        }
        else{
            return  "User not found";
        }
    }
}
