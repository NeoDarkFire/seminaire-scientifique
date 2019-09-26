import model.ModelFacade;

public class Encryptor {

    public static final String KEY = "awqprezorezo";

    public static void main(final String[] args) {
        final ModelFacade model = new ModelFacade();

        final String content = "\n" +
                "\n" +
                "Wiki Loves Monuments : photographiez un monument historique, aidez Wikipédia et gagnez ! En apprendre plus\n" +
                "Fréquence d'apparition des lettres en français\n" +
                "Sauter à la navigation\n" +
                "Sauter à la recherche\n" +
                "\n" +
                "Le calcul de la fréquence des lettres dans une langue est difficile et soumis à interprétation. On compte la fréquence des lettres d’un texte arbitrairement long, mais un certain nombre de paramètres influencent les résultats :\n" +
                "\n" +
                "    Le style narratif : s’il y a beaucoup de verbes à la 2e personne du pluriel (le vouvoiement, présent dans beaucoup de dialogues), il y aura significativement plus de « Z ».\n" +
                "    Le vocabulaire spécifique du document : si l’on parle de chemins de fer, il y aura beaucoup plus de « W » (wagon) ; si l’un des protagonistes se dénomme Loïs, le nombre d'« Ï » s’en ressentira.\n" +
                "    Le type de document : des petites annonces en France présenteront souvent le symbole Euro (€), qui est absent de la plupart des autres documents.\n" +
                "    Les paramètres techniques : on peut facilement calculer des statistiques sur des textes informatisés, mais souvent ceux-ci ne comportent pas de majuscules accentuées (car difficiles à entrer sur certains ordinateurs) et il arrive aux auteurs d'oublier des accents. La graphie de l’e-dans-l’o (œ) est impossible à représenter dans le codage latin-1 qui est souvent utilisé pour les textes en français. C'est un problème parce que « œ » n'est pas une ligature esthétique (optionnelle) mais une ligature linguistique (obligatoire), elle se prononce différemment de la suite de voyelles « oe » . Par exemple, « œ » va se prononcer [ɛ] dans œsophage alors que « oe » va se prononcer [ɔ.ɛ] dans coexistence.\n" +
                "    La présence de caractères non alphabétiques (symboles de ponctuation, chiffres, parenthèses et accolades, symboles mathématiques courants…) peut ou non être prise en compte ; la virgule, le point ou l’apostrophe sont par exemple plus fréquents que plus de la moitié des lettres[réf. souhaitée].\n" +
                "\n" +
                "Si ces paramètres ont un impact spectaculaire sur les symboles les moins fréquents (la fréquence du œ varie entre 0,002 % et 0,09 % pour trois textes pris au hasard)[réf. nécessaire], elle est également sensible même pour les lettres les plus fréquentes (l’ordre de fréquence des lettres A, S, I, T et N, qui sont les plus fréquentes à part E, fluctue d’un texte à l’autre).\n" +
                "Sommaire\n" +
                "\n" +
                "    1 Remarque importante\n" +
                "    2 Fréquence des caractères dans le corpus de Wikipédia en français\n" +
                "    3 Dans d'autres langues\n" +
                "    4 Références\n" +
                "    5 Annexes\n" +
                "        5.1 Articles connexes\n" +
                "        5.2 Liens externes\n" +
                "\n" +
                "Remarque importante\n" +
                "\n" +
                "La fréquence des lettres dans un texte diffère de celle de la liste des mots d’un dictionnaire. En effet, très peu de mots apparaissent au pluriel dans un dictionnaire, ce qui conduit la lettre s à y être moins fréquente. De plus, les lettres accentuées à et ù apparaissent dans un nombre très limité de mots, mais dont certains sont d'usage fréquent (à, où), ce qui contribue à modifier la fréquence relative de ces lettres.\n" +
                "\n" +
                "Le corpus de textes littéraires disponible sur le Net (par exemple sur le site de l’Association des bibliophiles universels (ABU)) permet à tout un chacun de se livrer en quelques minutes aux analyses de fréquence de lettres chez l’auteur de son choix.\n" +
                "Fréquence des caractères dans le corpus de Wikipédia en français\n" +
                "\n" +
                "Le corpus de Wikipédia en français, en 2008, a été segmenté en mots par le laboratoire CLLE-ERSS qui a ensuite recensé les occurrences de ces derniers1, permettant ainsi le calcul de la fréquence des caractères.\n" +
                "Fréquence des caractères2 sur le corpus de Wikipédia en français Rang \tCaractère \tNombre d'occurrences \tPourcentage\n" +
                "1 \te \t115 024 205 \t12,10\n" +
                "2 \ta \t67 563 628 \t7,11\n" +
                "3 \ti \t62 672 992 \t6,59\n" +
                "4 \ts \t61 882 785 \t6,51\n" +
                "5 \tn \t60 728 196 \t6,39\n" +
                "6 \tr \t57 656 209 \t6,07\n" +
                "7 \tt \t56 267 109 \t5,92\n" +
                "8 \to \t47 724 400 \t5,02\n" +
                "9 \tl \t47 171 247 \t4,96\n" +
                "10 \tu \t42 698 875 \t4,49\n" +
                "11 \td \t34 914 685 \t3,67\n" +
                "12 \tc \t30 219 574 \t3,18\n" +
                "13 \tm \t24 894 034 \t2,62\n" +
                "14 \tp \t23 647 179 \t2,49\n" +
                "15 \té \t18 451 937 \t1,94\n" +
                "17 \tg \t11 684 140 \t1,23\n" +
                "18 \tb \t10 817 171 \t1,14\n" +
                "19 \tv \t10 590 858 \t1,11\n" +
                "20 \th \t10 583 562 \t1,11\n" +
                "21 \tf \t10 579 192 \t1,11\n" +
                "28 \tq \t6 140 307 \t0,65\n" +
                "31 \ty \t4 351 953 \t0,46\n" +
                "35 \tx \t3 588 990 \t0,38\n" +
                "40 \tj \t3 276 064 \t0,34\n" +
                "43 \tè \t2 969 466 \t0,31\n" +
                "44 \tà \t2 966 029 \t0,31\n" +
                "45 \tk \t2 747 547 \t0,29\n" +
                "47 \tw \t1 653 435 \t0,17\n" +
                "48 \tz \t1 433 913 \t0,15\n" +
                "49 \tê \t802 211 \t0,08\n" +
                "52 \tç \t544 509 \t0,06\n" +
                "59 \tô \t357 197 \t0,04\n" +
                "62 \tâ \t320 837 \t0,03\n" +
                "63 \tî \t280 201 \t0,03\n" +
                "69 \tû \t164 516 \t0,02\n" +
                "70 \tù \t151 236 \t0,02\n" +
                "71 \tï \t138 221 \t0,01\n" +
                "77 \t\t73 751 \t0,01\n" +
                "79 \tü \t55 172 \t0,01\n" +
                "82 \të \t53 862 \t0,01\n" +
                "83 \tö \t51 020 \t0,01\n" +
                "84 \tí \t48 391 \t0,01\n" +
                "Dans d'autres langues\n" +
                "\n" +
                "[réf. nécessaire]\n" +
                "\n" +
                "Diagramme comparatif de la fréquence des lettres dans 11 langues.\n" +
                "Références\n" +
                "\n" +
                "CLLE-ERSS, « REDAC : Corpus texte WikipédiaFR2008 » [archive] (consulté le 9 aout 2016)\n" +
                "\n" +
                "    « Fréquence des caractères - Disposition de clavier francophone et ergonomique bépo » [archive] (consulté le 9 aout 2016)\n" +
                "\n" +
                "Annexes\n" +
                "Articles connexes\n" +
                "\n" +
                "    Analyse fréquentielle\n" +
                "\n" +
                "Liens externes\n" +
                "\n" +
                "    Fréquence des duets de lettres (digrammes) de l'alphabet [archive]\n" +
                "    Fréquence des caractères du français [archive]\n" +
                "    Duets de touches et de lettres (digrammes) les plus fréquentes au clavier [archive]\n" +
                "\n" +
                "    Portail de l’écriture \n" +
                "\n" +
                "Catégorie :\n" +
                "\n" +
                "    Langue française\n" +
                "\n" +
                "[+]\n" +
                "Menu de navigation\n" +
                "\n" +
                "    Non connecté\n" +
                "    Discussion\n" +
                "    Contributions\n" +
                "    Créer un compte\n" +
                "    Se connecter\n" +
                "\n" +
                "    Article\n" +
                "    Discussion\n" +
                "\n" +
                "    Lire\n" +
                "    Modifier\n" +
                "    Modifier le code\n" +
                "    Voir l’historique\n" +
                "\n" +
                "Rechercher\n" +
                "\n" +
                "    Accueil\n" +
                "    Portails thématiques\n" +
                "    Article au hasard\n" +
                "    Contact\n" +
                "\n" +
                "Contribuer\n" +
                "\n" +
                "    Débuter sur Wikipédia\n" +
                "    Aide\n" +
                "    Communauté\n" +
                "    Modifications récentes\n" +
                "    Faire un don\n" +
                "\n" +
                "Outils\n" +
                "\n" +
                "    Pages liées\n" +
                "    Suivi des pages liées\n" +
                "    Téléverser un fichier\n" +
                "    Pages spéciales\n" +
                "    Lien permanent\n" +
                "    Informations sur la page\n" +
                "    Élément Wikidata\n" +
                "    Citer cette page\n" +
                "\n" +
                "Imprimer / exporter\n" +
                "\n" +
                "    Créer un livre\n" +
                "    Télécharger comme PDF\n" +
                "    Version imprimable\n" +
                "\n" +
                "Dans d’autres langues\n" +
                "\n" +
                "    Català\n" +
                "    Deutsch\n" +
                "    English\n" +
                "    Esperanto\n" +
                "    Español\n" +
                "    Português\n" +
                "    中文\n" +
                "\n" +
                "Modifier les liens\n" +
                "\n" +
                "    La dernière modification de cette page a été faite le 24 décembre 2018 à 21:05.\n" +
                "    Droit d'auteur : les textes sont disponibles sous licence Creative Commons attribution, partage dans les mêmes conditions ; d’autres conditions peuvent s’appliquer. Voyez les conditions d’utilisation pour plus de détails, ainsi que les crédits graphiques. En cas de réutilisation des textes de cette page, voyez comment citer les auteurs et mentionner la licence.\n" +
                "    Wikipedia® est une marque déposée de la Wikimedia Foundation, Inc., organisation de bienfaisance régie par le paragraphe 501(c)(3) du code fiscal des États-Unis.\n" +
                "\n" +
                "    Politique de confidentialité\n" +
                "    À propos de Wikipédia\n" +
                "    Avertissements\n" +
                "    Contact\n" +
                "    Développeurs\n" +
                "    Déclaration sur les témoins (cookies)\n" +
                "    Version mobile\n" +
                "\n" +
                "    Wikimedia Foundation\t\n" +
                "    Powered by MediaWiki\t\n" +
                "\n";
        final String outFile = "src/test/resources/encrypted_test.txt";
        model.encrypt(content, KEY, outFile);
    }

}
