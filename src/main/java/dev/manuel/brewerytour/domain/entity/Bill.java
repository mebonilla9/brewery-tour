package dev.manuel.brewerytour.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bill")
public class Bill {

  @Id
  @SequenceGenerator(
    name = "bill_id_sequence",
    sequenceName = "bill_id_sequence"
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "bill_id_sequence"
  )
  private Integer id;
  private LocalDateTime date;
  private Double totalPrice;

  @ManyToOne
  @JoinColumn(name = "id_brewery")
  private Brewery brewery;

  @ManyToOne
  @JoinColumn(name = "id_user")
  private User user;

  @OneToMany
  @ToString.Exclude
  private List<BillDetail> details;


  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Bill bill = (Bill) o;
    return getId() != null && Objects.equals(getId(), bill.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
