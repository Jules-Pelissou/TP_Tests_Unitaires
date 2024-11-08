package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de
	// l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		// Les montants ont été correctement additionnés
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");
	}

	@Test
	// S3 : Ticket non imprimé si montant insuffisant
	void ticketNotPrinted(){
		machine.insertMoney(PRICE-1);
		assertFalse(machine.printTicket(), "Pas assez d'argent, ne doit pas imprimer");
	}

	@Test
	// S4 :
	void ticketIsPrinted(){
		machine.insertMoney(PRICE);
		assertTrue(machine.printTicket(), "Assez d'argent, doit imprimer");
	}

	@Test
	// S5 :
	void balanceIsDecremented(){
		machine.insertMoney(PRICE);
		machine.printTicket();
		assertEquals(0, machine.getBalance(), "La balance de la machine doit être décrémentée");
	}

	@Test
	// S6 :
	void totalIsUpdated(){
		machine.insertMoney(PRICE);
		machine.printTicket();
		assertEquals(PRICE, machine.getTotal(), "Le total doit être updaté");
	}

	@Test
	// S7 :
	void refundIsOK(){
		machine.insertMoney(PRICE);
		assertEquals(50, machine.refund(), "La machine doit rendre la monaie");
	}

	@Test
	// S8 :
	void refundResetBalance(){
		machine.insertMoney(PRICE);
		machine.refund();
		assertEquals(0, machine.getBalance(), "La balance doit être à 0");
	}

	@Test
	// S9 :
	void positiveMoney(){
		try{
			machine.insertMoney(-PRICE);
			fail("Il est impossible de rentrer un nombre négatif");
		}catch(IllegalArgumentException e){
		}
	}

	@Test
	// S10 :
	void createNegativeMachines(){
		try{
			machine = new TicketMachine(-PRICE);
			fail("Il est impossible de créer une machine ave cun prix négatif");
		}catch(IllegalArgumentException e){
		}
	}
}
