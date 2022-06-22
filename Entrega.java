import java.lang.AssertionError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.Set;

/*
 * Aquesta entrega consisteix en implementar tots els mètodes annotats amb el comentari "// TO DO".
 *
 * Cada tema té el mateix pes, i l'avaluació consistirà en:
 *
 * - Principalment, el correcte funcionament de cada mètode (provant amb diferents entrades). Teniu
 *   alguns exemples al mètode `main`.
 *
 * - La neteja del codi (pensau-ho com faltes d'ortografia). L'estàndar que heu de seguir és la guia
 *   d'estil de Google per Java: https://google.github.io/styleguide/javaguide.html . No és
 *   necessari seguir-la estrictament, però ens basarem en ella per jutjar si qualcuna se'n desvia
 *   molt.
 *
 * Per com està plantejada aquesta entrega, no necessitau (ni podeu) utilitzar cap `import`
 * addicional, ni mètodes de classes que no estiguin ja importades. El que sí podeu fer és definir
 * tots els mètodes addicionals que volgueu (de manera ordenada i dins el tema que pertoqui).
 *
 * Podeu fer aquesta entrega en grups de com a màxim 3 persones, i necessitareu com a minim Java 8.
 * Per entregar, posau a continuació els vostres noms i entregau únicament aquest fitxer.
 * - Nom 1: Josep Gabriel Fornés Reynés
 * - Nom 2: Antoni Bonet Trujillo
 * - Nom 3: Marc Sampol Villalonga
 *
 * L'entrega es farà a través d'una tasca a l'Aula Digital abans de la data que se us hagui
 * comunicat i vos recomanam que treballeu amb un fork d'aquest repositori per seguir més fàcilment
 * les actualitzacions amb enunciats nous. Si no podeu visualitzar bé algun enunciat, assegurau-vos
 * que el vostre editor de texte estigui configurat amb codificació UTF-8.
 */
class Entrega {
  /*
   * Aquí teniu els exercicis del Tema 1 (Lògica).
   *
   * Els mètodes reben de paràmetre l'univers (representat com un array) i els predicats adients
   * (per exemple, `Predicate<Integer> p`). Per avaluar aquest predicat, si `x` és un element de
   * l'univers, podeu fer-ho com `p.test(x)`, té com resultat un booleà. Els predicats de dues
   * variables són de tipus `BiPredicate<Integer, Integer>` i similarment s'avaluen com
   * `p.test(x, y)`.
   *
   * En cada un d'aquests exercicis us demanam que donat l'univers i els predicats retorneu `true`
   * o `false` segons si la proposició donada és certa (suposau que l'univers és suficientment
   * petit com per utilitzar la força bruta)
   */
  static class Tema1 {
    /*
     * És cert que ∀x,y. P(x,y) -> Q(x) ^ R(y) ?
     */
    static boolean exercici1(
        int[] universe,
        BiPredicate<Integer, Integer> p,
        Predicate<Integer> q,
        Predicate<Integer> r) {

            boolean a = true;
            boolean b = true;
            boolean fin = true;

            for (int x = 0; (fin) && x < universe.length; x++) {

                Integer xn = universe[x];

                for (int y = 0; (fin) && (y < universe.length); y++) {

                    Integer yn = universe[y];

                    if (!(p.test(xn, yn))) {    //Miram  el resultat del predicat P
                        a = false;
                    }

                    if (!(q.test(xn)) && (r.test(yn))) {  //Miram si la AND es compleix
                        b = false;
                    }

                    if ((a) && (!(b))) {  //Si no es compleix aquesta implicació, el boleà fin serà fals i ja no entrarà al bucle, ja que no serà veritat
                                            //que per tot a,b es compleix. Quan n'hi ha un que no es compleix, ja no és per a tot.
                        fin = false;
                    }

                }

            }
            System.out.println("Exercici 1 Tema 1: " + fin);
            return fin; // TO DO
    }

    /*
     * És cert que ∃!x. ∀y. Q(y) -> P(x) ?
     */
    static boolean exercici2(int[] universe, Predicate<Integer> p, Predicate<Integer> q) {
        boolean fin = false;
        int cont2 = 0;
        int cont = 0;

        for (int x = 0; (x < universe.length); x++) {

            int xn = universe[x];

            for (int y = 0; (y < universe.length); y++) {

                int yn = universe[y];

                if (!((q.test(yn) == true) && (p.test(xn) == false))) {  //cada pic que es compleix augmentam el contador per així poder mirar si s'ha complit tots els pics
                    cont++;
                }

                if (cont == universe.length) { //si s'han complit tots els pics, s'augmentarà el contador, però colem que aquest contador sigui 1, ja que és per un únic x
                    cont2++;

                }

            }

            cont = 0;

        }

        if (cont2 == 1) {  //Ja que volem que sigui en un únic x, si es compleix un pic, serà vertader
            fin = true;
        }

        System.out.println("Exercici 2 Tema 1: " + fin);
        return fin; // TO DO
    }

    /*
     * És cert que ¬(∃x. ∀y. y ⊆ x) ?
     *
     * Observau que els membres de l'univers són arrays, tractau-los com conjunts i podeu suposar
     * que cada un d'ells està ordenat de menor a major.
     */
    static boolean exercici3(int[][] universe) {
        boolean ex = false;

        boolean fin = false;
        for (int[] y : universe) {

            for (int[] x : universe) {

                if (x.length < y.length) {

                    ex = false;

                } else {

                    int n = 0;
                    for (int i = 0; i < y.length && n != y.length; i++) {

                        for (int j = 0; j < x.length; j++) {

                            if (y[i] == x[j]) {
                                n++;
                            }
                        }
                        if (n == y.length) {
                            ex = true;
                            break;
                        } else {
                            ex = false;
                        }
                    }
                }
            }
            if (!ex) {
                fin = false;
                System.out.println("Exercici 3 Tema 1: " + !fin);
                return !fin;
            } else {
                fin = true;
            }

        }

        fin = !fin;
        System.out.println("Exercici 3 Tema 1: " + fin);
        return fin; // TO DO
    }

    /*
     * És cert que ∀x. ∃!y. x·y ≡ 1 (mod n) ?
     */
    static boolean exercici4(int[] universe, int n) {
        int cont;
        boolean fin = true;

        for (int x = 1; (x <= universe.length) && (fin); x++) {
            
            cont = 0;

            for (int y = 1; y <= universe.length; y++) {

                if ((x * y) % n == 1) {     //Si el residu de x*y / n = 1, s'haurà complit la condició i augmentam 
                                            //el contador, ja que hem de mirar els pics que es compleix perque volem que sols es complesqui 1 pic
                    cont++;
                }

            }

            if (cont != 1){     //Miram si es compleix per una sola y
                fin = false;
            }
        }

        System.out.println("Exercici 4 Tema 1: " + fin);
        return fin; // TO DO
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // ∀x,y. P(x,y) -> Q(x) ^ R(y)

      assertThat(
          exercici1(
              new int[] { 2, 3, 5, 6 },
              (x, y) -> x * y <= 4,
              x -> x <= 3,
              x -> x <= 3
          )
      );

      assertThat(
          !exercici1(
              new int[] { -2, -1, 0, 1, 2, 3 },
              (x, y) -> x * y >= 0,
              x -> x >= 0,
              x -> x >= 0
          )
      );

      // Exercici 2
      // ∃!x. ∀y. Q(y) -> P(x) ?

      assertThat(
          exercici2(
              new int[] { -1, 1, 2, 3, 4 },
              x -> x < 0,
              x -> true
          )
      );

      assertThat(
          !exercici2(
              new int[] { 1, 2, 3, 4, 5, 6 },
              x -> x % 2 == 0, // x és múltiple de 2
              x -> x % 4 == 0  // x és múltiple de 4
          )
      );

      // Exercici 3
      // ¬(∃x. ∀y. y ⊆ x) ?

      assertThat(
          exercici3(new int[][] { {1, 2}, {0, 3}, {1, 2, 3}, {} })
      );

      assertThat(
          !exercici3(new int[][] { {1, 2}, {0, 3}, {1, 2, 3}, {}, {0, 1, 2, 3} })
      );

      // Exercici 4
      // És cert que ∀x. ∃!y. x·y ≡ 1 (mod n) ?

      assertThat(
          exercici4(
              new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
              11
          )
      );

      assertThat(
          !exercici4(
              new int[] { 0, 5, 7 },
              13
          )
      );
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 2 (Conjunts).
   *
   * De la mateixa manera que al Tema 1, per senzillesa tractarem els conjunts com arrays (sense
   * elements repetits). Per tant, un conjunt de conjunts d'enters tendrà tipus int[][].
   *
   * Les relacions també les representarem com arrays de dues dimensions, on la segona dimensió
   * només té dos elements. Per exemple
   *   int[][] rel = {{0,0}, {1,1}, {0,1}, {2,2}};
   * i també donarem el conjunt on està definida, per exemple
   *   int[] a = {0,1,2};
   *
   * Les funcions f : A -> B (on A i B son subconjunts dels enters) les representam donant int[] a,
   * int[] b, i un objecte de tipus Function<Integer, Integer> que podeu avaluar com f.apply(x) (on
   * x és un enter d'a i el resultat f.apply(x) és un enter de b).
   */
  static class Tema2 {
    /*
     * És `p` una partició d'`a`?
     *
     * `p` és un array de conjunts, haureu de comprovar que siguin elements d'`a`. Podeu suposar que
     * tant `a` com cada un dels elements de `p` està ordenat de menor a major.
     */
    static boolean exercici1(int[] a, int[][] p) {
        int con = a.length;
        boolean[] esta = new boolean[con];
        for (int i = 0; i < con; i++) {
            esta[i] = false;
        }
        boolean sol = true;
        int i = 0;
        int peq = a[0], gran = a[con - 1];
        for (int ay : a){
            boolean aparece = false;
            for (int[] py : p) {
                for (int pyy : py) {
                    if (pyy >= peq && pyy <= gran) {
                        if (ay == pyy) {
                            aparece = true;
                            if (!esta[i]) {
                                esta[i] = true;
                            } else {
                                sol = false;
                                System.out.println("Exercici 1 tema 2 :"+sol);
                                return sol;
                            }
                            break;
                        }
                    }else{
                        sol = false;
                        System.out.println("Exercici 1 tema 2 :"+sol);
                        return sol;
                    }
                }
            }
            if (!aparece) {
                sol = false; 
                System.out.println("Exercici 1 tema 2 :"+sol);
                return sol;
            }
            i++;
        }

        System.out.println("Exercici 1 tema 2 :" + sol);
      return sol; // TO DO
    }

    /*
     * Comprovau si la relació `rel` definida sobre `a` és un ordre parcial i que `x` n'és el mínim.
     *
     * Podeu soposar que `x` pertany a `a` i que `a` està ordenat de menor a major.
     */
    static boolean exercici2(int[] a, int[][] rel, int x) {
        
        boolean reflexiva = true;
        boolean antisimetrica = true;
        boolean transitiva = true;
        boolean fin = true;

        //Miram si es reflexiva
        for (int i = 0; i < a.length; i++) {
            if (!relacio(a[i], a[i], rel)) {
                reflexiva = false;
            }
        }

        //Miram si és antisimètrica
        for (int i = 0; i < rel.length - 1; i++) {
            for (int j = i + 1; j < rel.length; j++) {
                if (rel[i][0] == rel[j][1] && rel[i][1] == rel[j][0]) {
                    antisimetrica = false;
                }
            }
        }

        //Miram si és transitiva
        for (int[] rel1 : rel) {
            for (int[] rel2 : rel) {
                if (rel1[1] == rel2[0] && !relacio(rel1[0], rel2[1], rel)) {
                    transitiva = false;
                }
            }
        }
        
        //Si no es compleixen totes les condicions, el resultat serà fals
        if((!reflexiva) || (!antisimetrica) || (!transitiva)){
            fin = false;
        }
        
        //Miram si és minim
        for (int i = 0; i < a.length; i++) {
            if (!relacio(x, a[i], rel)) {
                fin = false;
            }
        }
        System.out.println("Exercici 2 Tema 2: " + fin);
        return fin; // TO DO
    }

    //Mètode que ens diu si els paràmetres a i b passats per paràmetre estan relacionats amn l'array rel
    private static boolean relacio(int i, int j, int[][] rel) {
        for (int[] rela : rel) {
            if (rela[0] == i && rela[1] == j) {
                return true;
            }
        }
        return false;
    }

    /*
     * Suposau que `f` és una funció amb domini `dom` i codomini `codom`.  Trobau l'antiimatge de
     * `y` (ordenau el resultat de menor a major, podeu utilitzar `Arrays.sort()`). Podeu suposar
     * que `y` pertany a `codom` i que tant `dom` com `codom` també estàn ordenats de menor a major.
     */
    static int[] exercici3(int[] dom, int[] codom, Function<Integer, Integer> f, int y) {
        int a = 0;
        for (int i : dom) {
            if (y == f.apply(i)){
                a++;
            }
        }

        int[] res = new int[a];

        if (a == 0){
            return res;
        }else{
            int jc = 0;
            for (int i : dom) {
                if (y == f.apply (i)) {
                    res [jc] = i;
                }
            }

        }
        Arrays.sort(res);
        return res; // TO DO
    }

    /*
     * Suposau que `f` és una funció amb domini `dom` i codomini `codom`.  Retornau:
     * - 3 si `f` és bijectiva
     * - 2 si `f` només és exhaustiva
     * - 1 si `f` només és injectiva
     * - 0 en qualsevol altre cas
     *
     * Podeu suposar que `dom` i `codom` estàn ordenats de menor a major. Per comoditat, podeu
     * utilitzar les constants definides a continuació:
     */
    static final int NOTHING_SPECIAL = 0;
    static final int INJECTIVE = 1;
    static final int SURJECTIVE = 2;
    static final int BIJECTIVE = INJECTIVE + SURJECTIVE;

    static int exercici4(int[] dom, int[] codom, Function<Integer, Integer> f) {
        
        boolean injectiva = true;
        boolean exhaustiva = true;
        int fin;

        //Es injectiva?
        for (int i = 0; (i < codom.length) && (injectiva); i++) {
            if (exercici3(dom, codom, f, codom[i]).length > 1) {
                injectiva = false;
            }
        }

        //Es exhaustiva?
        for (int i = 0; (i < codom.length) && (exhaustiva); i++) {
            if (exercici3(dom, codom, f, codom[i]).length < 1) {
                exhaustiva = false;
            }
        }        
        
        //Si es exhaustiva i injectiva, serà bijectiva
        if (injectiva && exhaustiva) {
            fin = BIJECTIVE;
        }else if (injectiva && !exhaustiva) { //Si es injectiva i no exhaustiva, serà injectiva
            fin = INJECTIVE;
        }else if (!injectiva && exhaustiva) {  //Si no és exhaustiva i és injectiva, serà echaustiva
            fin = SURJECTIVE;
        }else{  //Si no es ningun dels altres
            fin = NOTHING_SPECIAL;
        }

        //Si es exhaustiva i injectiva, serà bijectiva
        System.out.println("Exercici 4 Tema 2: " + fin);
        return fin; // TO DO
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // `p` és una partició d'`a`?

      assertThat(
          exercici1(
              new int[] { 1, 2, 3, 4, 5 },
              new int[][] { {1, 2}, {3, 5}, {4} }
          )
      );

      assertThat(
          !exercici1(
              new int[] { 1, 2, 3, 4, 5 },
              new int[][] { {1, 2}, {5}, {1, 4} }
          )
      );

      // Exercici 2
      // és `rel` definida sobre `a` d'ordre parcial i `x` n'és el mínim?

      ArrayList<int[]> divisibility = new ArrayList<int[]>();

      for (int i = 1; i < 8; i++) {
        for (int j = 1; j <= i; j++) {
          if (i % j == 0) {
            // i és múltiple de j, és a dir, j|i
            divisibility.add(new int[] { j, i });
          }
        }
      }

      assertThat(
          exercici2(
              new int[] { 1, 2, 3, 4, 5, 6, 7 },
              divisibility.toArray(new int[][] {}),
              1
          )
      );

      assertThat(
          !exercici2(
              new int[] { 1, 2, 3 },
              new int[][] { {1, 1}, {2, 2}, {3, 3}, {1, 2}, {2, 3} },
              1
          )
      );

      assertThat(
          !exercici2(
              new int[] { 1, 2, 3, 4, 5, 6, 7 },
              divisibility.toArray(new int[][] {}),
              2
          )
      );

      // Exercici 3
      // calcular l'antiimatge de `y`

      assertThat(
          Arrays.equals(
              new int[] { 0, 2 },
              exercici3(
                  new int[] { 0, 1, 2, 3 },
                  new int[] { 0, 1 },
                  x -> x % 2, // residu de dividir entre 2
                  0
              )
          )
      );

      assertThat(
          Arrays.equals(
              new int[] { },
              exercici3(
                  new int[] { 0, 1, 2, 3 },
                  new int[] { 0, 1, 2, 3, 4 },
                  x -> x + 1,
                  0
              )
          )
      );

      // Exercici 4
      // classificar la funció en res/injectiva/exhaustiva/bijectiva

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3 },
              new int[] { 0, 1, 2, 3 },
              x -> (x + 1) % 4
          )
          == BIJECTIVE
      );

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3 },
              new int[] { 0, 1, 2, 3, 4 },
              x -> x + 1
          )
          == INJECTIVE
      );

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3 },
              new int[] { 0, 1 },
              x -> x / 2
          )
          == SURJECTIVE
      );

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3 },
              new int[] { 0, 1, 2, 3 },
              x -> x <= 1 ? x+1 : x-1
          )
          == NOTHING_SPECIAL
      );
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 3 (Aritmètica).
   *
   */
  static class Tema3 {
    /*
     * Donat `a`, `b` retornau el màxim comú divisor entre `a` i `b`.
     *
     * Podeu suposar que `a` i `b` són positius.
     */
    static int exercici1(int a, int b) {
        int res = 0;
        boolean fin = false;

        //Si a >= b, entra aqui, ja que s'ha de fer sa divisió: num gran / num petit
        if (!fin && a >= b){
            fin = true;
            res = a % b;
            while (res != 0){  //Trobarem el mcd si el residu es 0, així que fins q no ho trobem seguim fent divisions
                a = b;
                b = res;
                res = a % b;
            }
            res = b;
        }else if(!fin && a < b){
            fin = true;
            res = b % a;
            while (res != 0){
                b = a;
                a = res;
                res = b % a;
            }
            res = a;
        }
        System.out.println("Exercici 1 tema 3: " + res);
      return res; // TO DO
    }

    /*
     * Es cert que `a``x` + `b``y` = `c` té solució?.
     *
     * Podeu suposar que `a`, `b` i `c` són positius.
     */
    //Tendrà solució si mcd de a,b és divisible entre c
    //Així que feim el mateix que a l'exercici anterior, però al final miram si mcd es divisible entre c
    static boolean exercici2(int a, int b, int c) {
        int res = 0;
        boolean fin = false;
        if ((!fin) && (a >= b)){
            fin = true;
            res = a % b;
            while (!(res == 0)){
                a = b;
                b = res;
                res = a % b;
            }
            res = b;
        }else if((!fin) && (a < b)){
            fin = true;
            res = b % a;
            while (!(res == 0)){
                b = a;
                a = res;
                res = b % a;
            }
            res = a;
        }

        if ((c % res) == 0){    //Serà solució si el residu de dividir c / mcd(a,b) és igual a 0.
            fin = true;
        }else{
            fin = false;
        }
        System.out.println("Exercici 2 Tema 3: " + fin);
      return fin; // TO DO
    }

    /*
     * Quin es l'invers de `a` mòdul `n`?
     *
     * Retornau l'invers sempre entre 1 i `n-1`, en cas que no existeixi retornau -1
     */
    //Para obtenir l'inversa, hem de fer  A * B mod N, amb B per valors entre 0 i N-1
    //L'invers modular de A mod N es el valor de B que fa que se complesqui que A * B mod N = 1
    static int exercici3(int a, int n) {
        int inversa = -1;
        for (int i = 0; (inversa == -1) && (i < n); i++){
            if(((a * i) % n) == 1){
                inversa = i;
            }
        }
        System.out.println("Exercici 3 Tema 3: " + inversa);
      return inversa; // TO DO
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // `mcd(a,b)`

      assertThat(
              exercici1(2, 4) == 2
      );

      assertThat(
              exercici1(1236, 984) == 12
      );

      // Exercici 2
      // `a``x` + `b``y` = `c` té solució?

      assertThat(
              exercici2(4,2,2)
      );
      assertThat(
              !exercici2(6,2,1)
      );
      // Exercici 3
      // invers de `a` mòdul `n`
      assertThat(exercici3(2, 5) == 3);
      assertThat(exercici3(2, 6) == -1);
    }
    
  }

  static class Tema4 {
    /*
     * Donada una matriu d'adjacencia `A` d'un graf no dirigit, retornau l'ordre i la mida del graf.
     */
    static int[] exercici1(int[][] A) {

        int uns = 0;
        int num = 0;

        for(int i = 0; i < A.length; i++){
            for(int j = 0 + i; j < A.length; j++){
                num = A[i][j];

                if(num == 1){
                    uns++;
                }
            }
        }
        System.out.println("Exercici 1 Tema 4: {" + A.length + ", " + uns + "}");
      return new int[]{A.length, uns}; // TO DO
    }

    /*
     * Donada una matriu d'adjacencia `A` d'un graf no dirigit, digau si el graf es eulerià.
     */
    static boolean exercici2(int[][] A) {

        int uns = 0;
        int num = 0;
        boolean fin = true;

        for(int i = 0; (i < A.length) && (fin); i++){
            for(int j = 0; (j < A.length) && (fin); j++){
                num = A[i][j];

                if(num == 1){
                    uns++;
                }
            }

            if (!((uns % 2) == 0)){
                fin = false;
            }
        }

        System.out.println("Exercici 2 Temaa 4: " + fin);
      return fin; // TO DO
    }

    /*
     * Donat `n` el número de fulles d'un arbre arrelat i `d` el nombre de fills dels nodes interiors,
     * retornau el nombre total de vèrtexos de l'arbre
     *
     */
    //Per poder solucionar aquest exercici, hem de saber que E = V-1 , 2E = Sumatori del nombre de graus dels nodes
    static int exercici3(int n, int d) {
        int vertex;

        //tenim que E = (n + 1 + k) - 1   ------  (n + 1 + k) = V  --------  el +1 (referència a l'arrel), es pot anular amb el -1
        //aixi en quedaria que E = n + k   ------   k = vertex interiors

        //Amb l'altre formula treum que 2 (n + k) = (n + d) + (d + 1)k   ----  (n + d)  suma de les fullees amb els fils dels vertex interiors
        //    (d + 1)k  -------  nombre de fiils dels vertex + 1 vertex pare. Tot això multiplicat per els vertex interiors

        //aixi ens queda que: 2n - (n + d) = (d + 1)k - 2)k   ------   k = [2n - (n + d)]/[(d + 1) - 2]

        int a = 2*n;
        int b = n+d;
        int c = d + 1;

        vertex = (a - b)/(c - 2);

        //Així tendrem el nombre de vertex interiors, i sols ens toca sumar-ho amb els vertex restants

        vertex = vertex + n + 1;

        System.out.println("Exercici 3 Tema 4: " + vertex);
      return vertex; // TO DO
    }

    /*
     * Donada una matriu d'adjacencia `A` d'un graf connex no dirigit, digau si el graf conté algún cicle.
     */

    //Hem de mirar si el que ens donen és un arbre o no. Per això hem de mirar 
    //si el nombre d'arestes és = al nombre de vèrtex - 1
    static boolean exercici4(int[][] A) {
        
        boolean fin;
        int cont = 0;

        for (int i = 0; i < A.length; i++){ //Miram les arestes que té el sistema que ens han passat
            for (int j = 0; j < A.length; j++){
                if (A[i][j] == 1){
                    A[i][j] = 0;
                    A[j][i] = 0;
                    cont++;
                }
            }

        }

        if(cont == (A.length - 1)){     //Si les arestes són iguals al vertex - 1, doncs es tractarà d'un arbre i no hi haurà cao cicle
            fin = false;
        }else{
            fin = true;
        }

        System.out.println("Exercici 4 Tema 4: " + fin);
        return fin; // TO DO
    }
    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // `ordre i mida`

      assertThat(
              Arrays.equals(exercici1(new int[][] { {0, 1, 0}, {1, 0, 1}, {0,1, 0}}), new int[] {3, 2})
      );

      assertThat(
              Arrays.equals(exercici1(new int[][] { {0, 1, 0, 1}, {1, 0, 1, 1}, {0 , 1, 0, 1}, {1, 1, 1, 0}}), new int[] {4, 5})
      );

      // Exercici 2
      // `Es eulerià?`

      assertThat(
              exercici2(new int[][] { {0, 1, 1}, {1, 0, 1}, {1, 1, 0}})
      );
      assertThat(
              !exercici2(new int[][] { {0, 1, 0}, {1, 0, 1}, {0,1, 0}})
      );
      // Exercici 3
      // `Quants de nodes té l'arbre?`
      assertThat(exercici3(5, 2) == 9);
      assertThat(exercici3(7, 3) == 10);

      // Exercici 4
      // `Conté algún cicle?`
      assertThat(
              exercici4(new int[][] { {0, 1, 1}, {1, 0, 1}, {1, 1, 0}})
      );
      assertThat(
              !exercici4(new int[][] { {0, 1, 0}, {1, 0, 1}, {0, 1, 0}})
      );

    }
  }

  /*
   * Aquest mètode `main` conté alguns exemples de paràmetres i dels resultats que haurien de donar
   * els exercicis. Podeu utilitzar-los de guia i també en podeu afegir d'altres (no els tendrem en
   * compte, però és molt recomanable).
   *
   * Podeu aprofitar el mètode `assertThat` per comprovar fàcilment que un valor sigui `true`.
   */
  public static void main(String[] args) {
    Tema1.tests();
    Tema2.tests();
    Tema3.tests();
    Tema4.tests();
  }

  static void assertThat(boolean b) {
    if (!b)
      throw new AssertionError();
  }
}

// vim: set textwidth=100 shiftwidth=2 expandtab :
