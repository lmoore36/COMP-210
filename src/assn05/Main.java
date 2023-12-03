package assn05;

public class Main {

    public static void main(String[] args) {
        testP1();
        testP2();
        testP3();
        testP4();
    }

    // test Part 1
    public static void testP1() {

        SimpleEmergencyRoom emergencyRoom = new SimpleEmergencyRoom();
        emergencyRoom.addPatient("Bella", 1);
        emergencyRoom.addPatient("Hari", 2);
        emergencyRoom.addPatient("Julia", 3);

        emergencyRoom.dequeue();

        System.out.println("Patients in the ER: ");
        for (Patient patient : emergencyRoom.getPatients()) {
            System.out.println(patient.getValue() + ", Priority: " + patient.getPriority());
        }
    }

    public static void testP2() {
        MaxBinHeapER testCase = new MaxBinHeapER();
        testCase.enqueue(2,45);
        testCase.enqueue(308390,23);
        testCase.enqueue(23440,39);
        testCase.enqueue(348750,449);
        System.out.println(testCase.getMax());
    }

    /*
    Part 3
     */
    public static void testP3() {

        MaxBinHeapER testER = new MaxBinHeapER(makePatients());
        Prioritized[] arr = testER.getAsArray();
        for(int i = 0; i < testER.size(); i++) {
            System.out.println("Value: " + arr[i].getValue() + ", Priority: " + arr[i].getPriority());
        }
    }

    public static void testP4() {
        double[] test = compareRuntimes();
        for(int i = 0; i < test.length; i++){
            System.out.println(i + ": " + test[i]);
        }
    }

    public static void fillER(MaxBinHeapER complexER) {
        for (int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }

    public static void fillER(SimpleEmergencyRoom simpleER) {
        for (int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for (int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    public static double[] compareRuntimes() {
        // Array which you will populate as part of Part 4
        double[] results = new double[4];

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);
        long startSimplePQ = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            simplePQ.dequeue();
        }
        long endSimplePQ = System.nanoTime();
        results[0] = endSimplePQ - startSimplePQ;
        results[1] = results[0] / 100000;

        MaxBinHeapER binHeap = new MaxBinHeapER<>();
        fillER(binHeap);
        long startBinHeap = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            binHeap.dequeue();
        }
        long endBinHeap = System.nanoTime();
        results[2] = endBinHeap - startBinHeap;
        results[3] = results[2] / 100000;

        return results;
    }
}



