package pers.wl.album.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TB_USER database table.
 * 
 */
@Entity
@Table(name="TB_USER")
@NamedQuery(name="TbUserModel.findAll", query="SELECT t FROM TbUserModel t")
public class TbUserModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;

	private String openid;

	private String unionid;

	private String username;

	public TbUserModel() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUnionid() {
		return this.unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}