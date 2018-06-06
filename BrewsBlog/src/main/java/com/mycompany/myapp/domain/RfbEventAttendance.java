package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A RfbEventAttendance.
 */
@Entity
@Table(name = "rfb_event_attendance")
public class RfbEventAttendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attendance_date")
    private LocalDate attendanceDate;

    @ManyToOne
    private RfbEvent rfbEvent;

    @OneToMany(mappedBy = "rfbEventAttendance")
    @JsonIgnore
    private Set<RfbUser> rfbUsers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public RfbEventAttendance attendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
        return this;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public RfbEvent getRfbEvent() {
        return rfbEvent;
    }

    public RfbEventAttendance rfbEvent(RfbEvent rfbEvent) {
        this.rfbEvent = rfbEvent;
        return this;
    }

    public void setRfbEvent(RfbEvent rfbEvent) {
        this.rfbEvent = rfbEvent;
    }

    public Set<RfbUser> getRfbUsers() {
        return rfbUsers;
    }

    public RfbEventAttendance rfbUsers(Set<RfbUser> rfbUsers) {
        this.rfbUsers = rfbUsers;
        return this;
    }

    public RfbEventAttendance addRfbUser(RfbUser rfbUser) {
        this.rfbUsers.add(rfbUser);
        rfbUser.setRfbEventAttendance(this);
        return this;
    }

    public RfbEventAttendance removeRfbUser(RfbUser rfbUser) {
        this.rfbUsers.remove(rfbUser);
        rfbUser.setRfbEventAttendance(null);
        return this;
    }

    public void setRfbUsers(Set<RfbUser> rfbUsers) {
        this.rfbUsers = rfbUsers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RfbEventAttendance rfbEventAttendance = (RfbEventAttendance) o;
        if (rfbEventAttendance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rfbEventAttendance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RfbEventAttendance{" +
            "id=" + getId() +
            ", attendanceDate='" + getAttendanceDate() + "'" +
            "}";
    }
}
