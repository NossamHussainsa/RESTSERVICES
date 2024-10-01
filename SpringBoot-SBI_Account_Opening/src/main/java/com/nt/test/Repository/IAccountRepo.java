package com.nt.test.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nt.test.Entity.AccountHolder;

import jakarta.transaction.Transactional;

@Repository

public interface IAccountRepo extends JpaRepository<AccountHolder, Integer> {
	@Query(value = "SELECT * FROM ACCOUNT_HOLDER WHERE ACCOUNT_HOLDER_IS_ACTIVE = 'Y'", nativeQuery = true)
	public List<AccountHolder> findAllActiveHolders();

	@Transactional
	@Modifying
	@Query(value = "UPDATE ACCOUNT_HOLDER SET ACCOUNT_HOLDER_IS_ACTIVE = 'N' WHERE ACCOUNT_HOLDER_ID = :Holder_id", nativeQuery = true)
	public void deleteActiveHolder(@Param("Holder_id") Integer Accountid);

	@Transactional
	@Modifying
	@Query(value = "UPDATE ACCOUNT_HOLDER SET ACCOUNT_HOLDER_AMOUNT = ACCOUNT_HOLDER_AMOUNT + :Amount WHERE ACCOUNT_HOLDER_ID = :Holder_id", nativeQuery = true)
	public void updateAmount(@Param("Holder_id") Integer Accountid, @Param("Amount") Double amount);

	boolean existsByNameAndBranchAndAccountType(String name, String branch, String accountType);

	public Optional<AccountHolder> findByIdAndIsActive(Integer id, String string);

	

}
