package csc366.jpademo;

import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
@Table(
        name = "rewardsAccount",     // may be omitted for default table naming
        uniqueConstraints = @UniqueConstraint(columnNames={"account_number"}) // requires @Column(name=...)
)
public class RewardsAccount {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique=true, name="account_number")
    private Integer account_number;

    @Column(name="total_points")
    private Integer total_points;

    public RewardsAccount() { }

    public RewardsAccount(Integer account_number, Integer total_points) {
        this.account_number = account_number;
        this.total_points = total_points;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAccountNumber() {
        return account_number;
    }
    public void setAccount_number(Integer account_number) {
        this.account_number = account_number;
    }

    public Integer getTotal_points() {
        return total_points;
    }
    public void setTotal_points(Integer total_points) {
        this.total_points = total_points;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , RewardsAccount.class.getSimpleName() + "[" , "]");
        sj.add(id.toString()).add(account_number.toString()).add(total_points.toString());
        return sj.toString();
    }
}
