package org.jesuitas.TFG.modelo.usuario;

public interface UserDetails {

    public String getPassword();

    public String getUsername();

    public boolean isAccountNonExpired();

    public boolean isAccountNonLocked();

    public boolean isCredentialsNonExpired();

    public boolean isEnabled();

}
