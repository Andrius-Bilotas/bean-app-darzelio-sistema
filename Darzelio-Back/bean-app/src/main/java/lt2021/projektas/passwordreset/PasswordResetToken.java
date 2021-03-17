package lt2021.projektas.passwordreset;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lt2021.projektas.userRegister.User;

@Entity
public class PasswordResetToken {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "Token")
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public PasswordResetToken() {
		// TODO Auto-generated constructor stub
	}

	public PasswordResetToken(User user) {
		super();
		this.user = user;
		var tomorrow = new Date();
		long day = 24 * 60 * 60 * 1000;
		tomorrow.setTime(tomorrow.getTime() + day);
		this.expiryDate = tomorrow;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
