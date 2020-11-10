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
        String stem = verb.substring(0, verb.length() - 2);
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
            conjugations.add("tu", "ce");
            conjugations.add("usted", "ces");
            conjugations.add("nosotros", "cemos");
            conjugations.add("ustedes", "cen");
            answer = threeLetterStem() + conjugations.getValue(subject);
        } else if (threeLetterEnding().equals("uir") && !subject.equals("nosotros")) {
            addPresentErIr();
            answer = getStem() + "y" + conjugations.getValue(subject);
        } else if (threeLetterEnding().equals("uar") && !subject.equals("nosotros")) {
            addPresentAr();
            answer = threeLetterStem() + "ú" + conjugations.getValue(subject);
        } else if (threeLetterEnding().equals("iar") && !subject.equals("nosotros")) {
            addPresentAr();
            answer = threeLetterStem() + "í" + conjugations.getValue(subject);
        } else return false;
        return true;
    } //end of checkIrreg method]

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
            yoChange.add("saber", "sé");
            yoChange.add("poner", "pongo");
            yoChange.add("dar", "doy");
            yoChange.add("caber", "caigo");
            yoChange.add("oir", "oigo");
            yoChange.add("gir", "jo");
            if (yoChange.getValue(verb) != null) {
                answer = yoChange.getValue(verb);
                return true;
            }
        }
        return false;
    }

    private boolean conjugateStemIrreg() {
        switch (verb) {
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
                conjugations.add("tu", "estás");
                conjugations.add("usted", "está");
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
                conjugations.add("nosotros", "oímos");
                conjugations.add("ustedes", "oyen");
                answer = conjugations.getValue(subject);
                break;
            case "reir":
                conjugations.add("yo", "rió");
                conjugations.add("tu", "ríes");
                conjugations.add("usted", "ríe");
                conjugations.add("nosotros", "reímos");
                conjugations.add("ustedes", "ríen");
                answer = conjugations.getValue(subject);
                break;
            default:
                return false;
        }
        return true;
    }


    private String threeLetterEnding() {
        return verb.substring(verb.length() - 3);
    }

    private String threeLetterStem() {
        return verb.substring(0, verb.length() - 3);
    }
} //end of PresentCard class
