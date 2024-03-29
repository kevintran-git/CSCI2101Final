import java.util.List;

/**
 * This class holds the methods needed to create a flashcard in the present tense
 */
public class PresentCard extends FlashCard {

    PresentCard(String verb, String subject) {
        super(verb, subject);
    } //end of constructor

    /**
     * a method to conjugate the verb
     *
     * @return string the conjugated verb
     */
    @Override
    public void conjugateVerb() {
        String ending = findEnding();
        String stem = getStem();
        if (!conjugateIrreg()) {
            if (ending.equals("ar")) {
                addPresentAr();
                answer = stem + conjugations.getValue(subject);
            } //end of if
            else if (ending.equals("er") || ending.equals("ir")) {
                if (subject.equals("nosotros") && ending.equals("ir"))
                    answer = stem + "imos";
                else {
                    addPresentErIr();
                    answer = stem + conjugations.getValue(subject);
                } // end of if
            } //end of if
            else {
                throw new IllegalArgumentException(verb + " doesn't end of ar, er, or ir.");
            }  //end of if
        } //end of if
    } //end of conjugateVerb method

    /**
     * a method to add the correct present endings to a dictionary
     */
    private void addPresentAr() {
        conjugations.add("yo", "o");
        conjugations.add("tu", "as");
        conjugations.add("usted", "a");
        conjugations.add("nosotros", "amos");
        conjugations.add("ustedes", "an");
    } //end of addPresentAr method

    /**
     * a method to add the correct present endings to a dictionary
     */
    private void addPresentErIr() {
        conjugations.add("yo", "o");
        conjugations.add("tu", "es");
        conjugations.add("usted", "e");
        conjugations.add("nosotros", "emos");
        conjugations.add("ustedes", "en");
    } //end of addPresentErIr method

    /**
     * a method to check if the verb given is irregular
     *
     * @return true if it is irregular, false if not
     */
    @Override
    protected boolean conjugateIrreg() {
        if (conjugateStemIrreg() || yoChange());
        else if (threeLetterEnding().equals("cer") || threeLetterEnding().equals("cir")) {
            conjugations.add("yo", "zco");
            conjugations.add("tu", "ces");
            conjugations.add("usted", "ce");
            conjugations.add("nosotros", "cemos");
            conjugations.add("ustedes", "cen");
            answer = threeLetterStem() + conjugations.getValue(subject);
        } else if (threeLetterEnding().equals("uir") && !subject.equals("nosotros")) {
            addPresentErIr();
            answer = getStem() + "y" + conjugations.getValue(subject);
        } else if (threeLetterEnding().equals("uar") && !subject.equals("nosotros")) {
            addPresentAr();
            answer = threeLetterStem() + "u" + conjugations.getValue(subject);
        } else if (threeLetterEnding().equals("iar") && !subject.equals("nosotros")) {
            addPresentAr();
            answer = threeLetterStem() + "i" + conjugations.getValue(subject);
        } else return false;
        return true;
    } //end of checkIrreg method]

    /**
     * This method is used to conjugate irregular verbs
     * that are irregular in only the "yo" form
     * @return true if the verb was conjugated
     * false if otherwise
     */
    private boolean yoChange() {
        if (subject.equals("yo")) {
            VerbDictionary<String, String> yoChange = new VerbDictionary<>();
            yoChange.add("hacer", "hago");
            yoChange.add("decir", "digo");
            yoChange.add("traer", "traigo");
            yoChange.add("salir", "salgo");
            yoChange.add("tener", "tengo");
            yoChange.add("caer", "caigo");
            yoChange.add("valer", "valgo");
            yoChange.add("venir", "vengo");
            yoChange.add("saber", "se");
            yoChange.add("poner", "pongo");
            yoChange.add("dar", "doy");
            yoChange.add("caber", "caigo");
            yoChange.add("oir", "oigo");
            yoChange.add("gir", "jo");
            if (yoChange.getValue(verb.toLowerCase()) != null) {
                answer = yoChange.getValue(verb.toLowerCase());
                return true;
            } // end if
        } // end if
        return false;
    } // end of yoChange

    /**
     * This method is used to conjugate irregular stem-changing verbs
     * @return true if the verb has been conjugated
     * false if otherwise
     */
    private boolean conjugateStemIrreg() {
        switch (verb.toLowerCase()) {
            case "haber":
                conjugations.add("yo", "he");
                conjugations.add("tu", "has");
                conjugations.add("usted", "ha");
                conjugations.add("nosotros", "hemos");
                conjugations.add("ustedes", "han");
                answer = conjugations.getValue(subject);
                break;
            case "estar":
                conjugations.add("yo", "estoy");
                conjugations.add("tu", "estas");
                conjugations.add("usted", "esta");
                conjugations.add("nosotros", "estamos");
                conjugations.add("ustedes", "estan");
                answer = conjugations.getValue(subject);
                break;
            case "ser":
                conjugations.add("yo", "soy");
                conjugations.add("tu", "eres");
                conjugations.add("usted", "es");
                conjugations.add("nosotros", "somos");
                conjugations.add("ustedes", "son");
                answer = conjugations.getValue(subject);
                break;
            case "ir":
                conjugations.add("yo", "voy");
                conjugations.add("tu", "vas");
                conjugations.add("usted", "va");
                conjugations.add("nosotros", "vamos");
                conjugations.add("ustedes", "van");
                answer = conjugations.getValue(subject);
                break;
            case "oir":
                conjugations.add("yo", "oigo");
                conjugations.add("tu", "oyes");
                conjugations.add("usted", "oye");
                conjugations.add("nosotros", "oimos");
                conjugations.add("ustedes", "oyen");
                answer = conjugations.getValue(subject);
                break;
            case "reir":
                conjugations.add("yo", "rio");
                conjugations.add("tu", "ries");
                conjugations.add("usted", "rie");
                conjugations.add("nosotros", "reimos");
                conjugations.add("ustedes", "rien");
                answer = conjugations.getValue(subject);
                break;
            default:
                return false;
        } // end switch
        return true;
    } // end conjugateStemIrreg


    /**
     * This method is used to get the three letter ending
     * of a verb
     * @return the last three letters of a verb
     */
    private String threeLetterEnding() {
        return verb.substring(verb.length() - 3);
    } //end of threeLetterEnding method

    /**
     * This method is used to get the stem of a verb with a
     * three letter ending
     * @return the stem of the verb
     */
    private String threeLetterStem() {
        return verb.substring(0, verb.length() - 3);
    } //end of threeLetterStem method

    /**
     * This method checks if a verb is an irregular stem changing verb
     * and returns the correct stem for conjugation
     * @return the stem for conjugation
     */
    @Override
    protected String getStem(){
        if (subject.toLowerCase().equals("nosotros"))
            return super.getStem(); // end if
        if(List.of("pedir", "decir", "seguir", "servir", "competir", "elegir",
                "cerregir", "vestir", "freir", "gemir", "repetir").contains(verb.toLowerCase()))
            for (int i = verb.length() - 1; i > 0; i--)
            {
                if (verb.charAt(i) == 'e')
                    return verb.substring(0, i) + "i" + verb.substring(i + 1, verb.length() - 2);
            } // end of for
        if(List.of("pensar", "empezar", "comenzar", "preferir", "acertar", "tener",
                "venir", "cerrar", "mentir", "fregar", "hervir", "confesar", "defender",
                "negar", "sentir", "querer").contains(verb.toLowerCase())) // end if
            for (int i = super.getStem().length() - 1; i > 0; i--)
            {
                if (verb.charAt(i) == 'e')
                    return verb.substring(0, i) + "ie" + verb.substring(i + 1, super.getStem().length());
            } // end of for
        if(List.of("dormir", "almorzar", "morir", "probar", "mover", "colgar", "mostrar",
                "contar", "costar", "recordar", "volver", "resolver", "solver").contains(verb.toLowerCase()))
            return super.getStem().replaceAll("o", "ue");
        if (verb.toLowerCase().equals("jugar"))
            return "jueg";
        return super.getStem(); // end if
    } // end getStem

    /**
     * This method will return "Present", which is the tense of this card
     * @return: "Present"
     */
    public String getTense(){
        return "Present";
    } //end of getTense method

} //end of PresentCard class
