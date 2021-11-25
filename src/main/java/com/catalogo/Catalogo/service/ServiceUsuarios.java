package com.catalogo.Catalogo.service;

import com.catalogo.Catalogo.model.Usuarios;
import com.catalogo.Catalogo.repository.RepositoryUsuarios;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUsuarios {
    @Autowired
    
    private RepositoryUsuarios mCrUsuarios;
    
    public List<Usuarios> getAll(){
        return mCrUsuarios.getAll();
    }
    
    public Optional<Usuarios> getUser(int idUser){
        return mCrUsuarios.getUser(idUser);
    }
    
    public Usuarios save(Usuarios user){
        if (user.getId()== null) {
            return mCrUsuarios.save(user);
        }else{
            Optional<Usuarios> evt = mCrUsuarios.getUser(user.getId());
            if(evt.isEmpty()){
                return mCrUsuarios.save(user);
            }else{
                return user;
            }
        }
    }
    
    public Usuarios update(Usuarios user){
        if(user.getId()!= null){
            Optional<Usuarios> evt = mCrUsuarios.getUser(user.getId());
            if(!evt.isEmpty()){
                if(user.getEmail() != null){
                    evt.get().setEmail(user.getEmail());
                }
                
                if(user.getPassword() != null){
                    evt.get().setPassword(user.getPassword());
                }
                
                if(user.getName() != null){
                    evt.get().setName(user.getName());
                }
            }
            
            return mCrUsuarios.save(evt.get());
        }
        return user;
    }
    
    public boolean delete(int idUser){
        Optional<Usuarios> evt = mCrUsuarios.getUser(idUser);
        if(!evt.isEmpty()){
            mCrUsuarios.delete(idUser);
            return true;
        }
        
        return false;
    }
    
    public boolean getUserEmail(String email){
        Optional<Usuarios> evt = mCrUsuarios.getUserByEmail(email);
        return !evt.isEmpty();
    }
    
    public Optional<Usuarios> getUserEmailPass(String email, String password){
        
        //JsonObject json =
        
        Optional<Usuarios> evt = mCrUsuarios.getUserEmailPass(email, password);
        if(!evt.isEmpty()){
            return mCrUsuarios.getUserEmailPass(email, password);
        }else{
            Usuarios usuario = new Usuarios();
            usuario.setId(null);
            usuario.setEmail("neslop30@gmail.com");
            usuario.setPassword("Ne$lop2650");
            usuario.setName("NO DEFINIDO");
            
            Optional<Usuarios> evt1 = Optional.of(usuario);
            
            return evt1;
        }
    }
}
