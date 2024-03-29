/**
 * This class holds the methods needed to create a card in the imperfect tense
 */
public class ImperfectCard extends FlashCard {

    ImperfectCard(String verb, String subject) {
        super(verb, subject);
    } //end of constructor

    /**
     * a method to conjugate the verb
     *
     */
    @Override
    public void conjugateVerb() {
        String ending = findEnding();
        String stem = verb.substring(0, verb.length() - 2);
        if (!conjugateIrreg()) {
         if (ending.equals("ar")) {
                addImperfectAr();
                answer = stem + conjugations.getValue(subject);
            } //end of else if
            else if (ending.equals("er") || ending.equals("ir")) {
                addImperfectErIr();
                answer = stem + conjugations.getValue(subject);
            } //end of else if
            else {
                throw new IllegalArgumentException(verb + " doesn't end of ar, er, or ir.");
            } //end of else
        } //end of if
    } //end of conjugateVerb method

    /**
     * a method to add the correct imperfect endings to a dictionary
     */
    private void addImperfectAr() {
        conjugations.add("yo", "aba");
        conjugations.add("tu", "abas");
        conjugations.add("usted", "aba");
        conjugations.add("nosotros", "abamos");
        conjugations.add("ustedes", "aban");
    } //end of addImperfectAr method

    /**
     * a method to add the correct imperfect endings to a dictionary
     */
    private void addImperfectErIr() {
        conjugations.add("yo", "ia");
        conjugations.add("tu", "ias");
        conjugations.add("usted", "ia");
        conjugations.add("nosotros", "iamos");
        conjugations.add("ustedes", "ian");
    } //end of addImperfectAr method


    /**
     * a method to conjugate irregular verbs
     *
     * @return true if the verb has been conjugated
     * false if otherwise
     */
    @Override
    protected boolean conjugateIrreg() {
        if (verb.equals("ver"))
        {
            conjugations.add("yo", "veia");
            conjugations.add("tu", "veias");
            conjugations.add("usted", "veia");
            conjugations.add("nosotros", "veiamos");
            conjugations.add("ustedes", "veian");
            answer = conjugations.getValue(subject);
        } // end if
        else if (verb.equals("ser"))
        {
            conjugations.add("yo", "era");
            conjugations.add("tu", "eras");
            conjugations.add("usted", "era");
            conjugations.add("nosotros", "eramos");
            conjugations.add("ustedes", "eran");
            answer = conjugations.getValue(subject);
        } // end else if
        else if (verb.equals("ir"))
        {
            conjugations.add("yo", "iba");
            conjugations.add("tu", "ibas");
            conjugations.add("usted", "iba");
            conjugations.add("nosotros", "ibamos");
            conjugations.add("ustedes", "iban");
            answer = conjugations.getValue(subject);
        } // end else if
        else return false;
        return true;
    } //end of conjugateIrreg method

    /**
     * This method will return "Imperfect", which is the tense of this card
     * @return: "Imperfect"
     */
    public String getTense(){
        return "Imperfect";
    } //end of getTense method
} //end of ImperfectCard class
