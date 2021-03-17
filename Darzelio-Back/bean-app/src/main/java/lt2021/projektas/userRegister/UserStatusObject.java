package lt2021.projektas.userRegister;

import java.util.List;

import lt2021.projektas.child.ChildStatusObject;

public class UserStatusObject {
	
	private boolean admissionActive;
	private boolean passwordChanged;
	private boolean detailsFilled;
	private boolean childRegistered;
	private List<ChildStatusObject> children;
	
	public UserStatusObject() {
		// TODO Auto-generated constructor stub
	}

	public UserStatusObject(boolean admissionActive, boolean passwordChanged, boolean detailsFilled, boolean childRegistered,
			List<ChildStatusObject> children) {
		super();
		this.admissionActive = admissionActive;
		this.passwordChanged = passwordChanged;
		this.detailsFilled = detailsFilled;
		this.childRegistered = childRegistered;
		this.children = children;
	}

	public boolean isPasswordChanged() {
		return passwordChanged;
	}

	public void setPasswordChanged(boolean passwordChanged) {
		this.passwordChanged = passwordChanged;
	}

	public boolean isDetailsFilled() {
		return detailsFilled;
	}

	public void setDetailsFilled(boolean detailsFilled) {
		this.detailsFilled = detailsFilled;
	}

	public boolean isChildRegistered() {
		return childRegistered;
	}

	public void setChildRegistered(boolean childRegistered) {
		this.childRegistered = childRegistered;
	}

	public List<ChildStatusObject> getChildren() {
		return children;
	}

	public void setChildren(List<ChildStatusObject> children) {
		this.children = children;
	}

	public boolean isAdmissionActive() {
		return admissionActive;
	}

	public void setAdmissionActive(boolean admissionActive) {
		this.admissionActive = admissionActive;
	}
	
	

}
