package com.fsd.assignment.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="subject")
public class Subject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(updatable = false, nullable = false, name="subjectId")
	private Long subjectId;
	private String subtitle;
	@Column(name="durationInHours")
	private int durationInHours;
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy="subject")
	@JsonManagedReference
	private Set<Book> reference;
	
	public Subject() {
		super();
	}

	public Subject(Long subjectId, String subtitle, int durationInHours, Set<Book> reference) {
		this.subjectId = subjectId;
		this.subtitle = subtitle;
		this.durationInHours = durationInHours;
		this.reference = reference;
	}

	public Subject(Long subjectId, String subtitle, int durationInHours) {
		this.subjectId = subjectId;
		this.subtitle = subtitle;
		this.durationInHours = durationInHours;
	}

	/**
	 * @return the subjectId
	 */
	public Long getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId
	 *            the subjectId to set
	 */
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * @param subtitle
	 *            the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	/**
	 * @return the durationInHours
	 */
	public int getDurationInHours() {
		return durationInHours;
	}

	/**
	 * @param durationInHours
	 *            the durationInHours to set
	 */
	public void setDurationInHours(int durationInHours) {
		this.durationInHours = durationInHours;
	}

	/**
	 * @return the reference
	 */
	public Set<Book> getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(Set<Book> reference) {
		this.reference = reference;
	}

	public void addReference(Book book) {
		if (null == this.reference) {
			this.reference = new HashSet<Book>();
		}
		this.reference.add(book);
	}

	public void deleteReference(Book book) {
		if (null != this.reference) {
			Iterator<Book> itr = this.reference.iterator();
			while (itr.hasNext()) {
				Book b = itr.next();
				if (b.equals(book)) {
					itr.remove();
				}
			}
			this.reference.add(book);
		}
	}

	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		if (obj instanceof Subject) {
			Subject s = (Subject) obj;
			if (this.subjectId == s.subjectId && this.durationInHours == s.durationInHours
					&& this.subtitle.equals(s.subtitle)) {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public int hashCode() {
		return Objects.hash(subjectId, subtitle, durationInHours);
	}

	@Override
	public String toString() {
		StringBuffer references = new StringBuffer("");
		if (null != this.reference) {
			for (Book b : this.reference) {
				references.append(b.toString());
			}
		}

		return new StringBuffer("Subject: subjectId[").append(subjectId).append("], subtitle[")
				.append(subtitle).append("], durationInHours[").append(durationInHours)
				.append("], reference[").append(references.toString()).append("]").toString();
	}

}
