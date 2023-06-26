package ch.bbw.m183.vulnerapp.datamodel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @Email
    @NotBlank(message = "Your E-Mail-Address is mandatory!")
    String username;

    @Column
    @NotBlank(message = "Your Fullname is mandatory!")
    @Length(min = 1, max = 30, message = "Your Fullname has to be at least 1 characters long, and within 30 characters.")
    String fullname;

    @Column
    @NotBlank(message = "Your password is mandatory!")
    @Length(min = 7, message = "Your Password has to be at least 7 characters long.")
    String password;

    @Column
    @NotBlank(message = "Your Role is mandatory!")
    String role;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();

        list.add(new SimpleGrantedAuthority(role));

        if (role.equals("ADMIN")) {
            list.add(new SimpleGrantedAuthority("USER"));
        }

        return list;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
