package dev.manuel.brewerytour.domain.entity;

import dev.manuel.brewerytour.application.lasting.EState;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "booking")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Booking {

  @Id
  @SequenceGenerator(
    name = "booking_id_sequence",
    sequenceName = "booking_id_sequence"
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "booking_id_sequence"
  )
  private Integer id;
  private LocalDateTime date;
  private Integer quantity;

  @Enumerated(EnumType.STRING)
  private EState state;

  @ManyToOne
  @JoinColumn(name = "id_brewery")
  private Brewery brewery;

  @ManyToOne
  @JoinColumn(name = "id_user")
  private User user;

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Booking booking = (Booking) o;
    return getId() != null && Objects.equals(getId(), booking.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
