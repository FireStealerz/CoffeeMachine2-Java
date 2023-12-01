package com.univa.cafetera;

public class Cafetera {

    private int capacidadMaxima;

    private int capacidadActual;

    private final int CAPACIDAD_MAXIMA = 1000;

    private final int GRAN = 150;

    private final int MUG = 230;

    private final int ALTO = 414;

    private enum MachineState { //Estados de la maquina
        MAIN, AGREGAR_CAFE, SERVIR,
    }

    private MachineState state;

    Cafetera() {  //Inicializando cafetera vacia
        setCapacidadMaxima(1000);
        setCapacidadActual(0);
        machineMenu();
    }

    Cafetera(int maxima) { // Iinicalisando cafetera llena
        setCapacidadMaxima(maxima);
        setCapacidadActual(maxima);
        machineMenu();
    }

    Cafetera(int maxima, int actual) {  //INicializando cafetera con cantidad personalisada
        if (actual <= CAPACIDAD_MAXIMA && maxima == CAPACIDAD_MAXIMA) {
            setCapacidadMaxima(maxima);
            setCapacidadActual(actual);
        } else if (actual > maxima) {
            setCapacidadMaxima(maxima);
            setCapacidadActual(maxima);
            System.out.println("Capacidad actual no puede ser mayor que la maxima ajustando actual al limite");
        }
        machineMenu();
    }

    Recetas Gran = new Recetas(GRAN), Mug = new Recetas(MUG), Alto = new Recetas(ALTO); //Inicializa opciones de recetas

    Recetas[] recetas = { Gran, Mug, Alto }; //Organiza las recestas en un array para poder manipularlas


    //Gettesr y Setters para cambiar y acceder a los valores privados
    private int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    private void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    private int getCapacidadActual() {
        return capacidadActual;
    }

    private void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    private void llenarCafetera() { // Llena cafetera al maximo
        System.out.println("Llenando cafetera");
        setCapacidadActual(CAPACIDAD_MAXIMA);
    }

    private void restCoffee(int coffee) { //Resta la cantidad de cafe que se esta utilizando
        setCapacidadActual(getCapacidadActual() - coffee);
    }

    private void vaciarCafetera() { //Vacia la cafetera
        setCapacidadActual(0);
    }

    private void agregarCafe(int cafe) {  //Agreaga cafe solo si no excede la capacidad max 1000
        if (getCapacidadActual() + cafe > getCapacidadMaxima()) {
            System.out.println("El cafe que desea agregar excede la capacidad de la cafetera");
        }else {
            setCapacidadActual(getCapacidadActual() + cafe);
        }
    }

    public void machineAction(String input) {
        switch (state) {
            case MAIN -> machineState(input); //Menu prinicpal accepta String
            case SERVIR -> {   //Menu de compra hacepta string lo convierte en int
                buy(input);
                machineMenu();
            }
            case AGREGAR_CAFE -> {  //Directamente convierte a String a Int
                try {
                    int cantidadCafe = Integer.parseInt(input);
                    agregarCafe(cantidadCafe);
                }catch (NumberFormatException e) {
                    System.out.println("Se necesita escribir un numero!");  //Checa que el valor sea un numero
                }
                machineMenu();
            }
        }
    }

    private void machineState (String action){ ///M. Principal
        switch (action) {
            case "llenar":
                System.out.println("Llenando cafetera!");
                System.out.println("Capcidad Maxima: " + getCapacidadMaxima() + "\n" +
                "Capacidad Actual: " + getCapacidadActual());
                llenarCafetera();
                machineMenu();
                break;
            case "vaciar":
                System.out.println("Vaciando cafetera!");
                vaciarCafetera();
                machineMenu();
                break;
            case "comprar":
                System.out.println("Que desea tomar? 1- Gran Lungo 2 - Mug 3 - Alto (escriba el numero) \n" +
                        "O escriba atras para regresar al menu principal.");
                state = MachineState.SERVIR;  //Estado
                break;
            case "agregar":
                System.out.println("Cuanto cafe desea agregar?");
                state = MachineState.AGREGAR_CAFE;  //Estado
                break;
            case "ver":
                System.out.println("Capcidad actual es: " + getCapacidadActual());
                machineMenu();
                break;
            case "salir":
                System.out.println("Apagando la maquina regrese pronto!");
                System.exit(0);
                break;
        }
    }

    private void buy(String input){
        int coffee;
        int check;
        if (input.equals("atras")) { //Te da la opcion para regresar al menu principal y no hace nada
            machineMenu();
            return;
        }else {
            coffee = Integer.parseInt(input); //Convierte el Strgin en int para acceder al array de recetas
        }
        check = check(coffee);
        if(check == 1) {
            coffee -= 1;
            restCoffee(recetas[coffee].getCafe()); //Resta solo la cantidad del cafe
        } else if (check == 2) {
            restCoffee(getCapacidadActual()); ///Vacia la cafetera
        }
    }

    private int check(int selection) {
        selection -= 1;
        if (getCapacidadActual() < recetas[selection].getCafe()) {
            System.out.println("No hay sufieciente cafe, sirviendo lo que queda! " + getCapacidadActual() + "c.c.");
            return 2;
        } else if (getCapacidadActual() > recetas[selection].getCafe()) {
            return 1;
        }
        return 0;
    }
    private void machineMenu() {
        state = MachineState.MAIN;  //Estado
        System.out.println("Bienvenido a la CAFE UNIVA!,que desea hacer? (llenar, comprar, vaciar, agregar, salir, " +
                "\"ver\" capacidad actual)");
    }
}
