package model;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.*;

class DecryptTest {

    @Test
    void it_should_not_allow_initial_keys_bigger_than_size() {
        try {
            Decrypt.decrypt("test 123".getBytes(), 2, "big");
            fail();
        } catch (final InvalidParameterException e) {
            return;
        }
        fail();
    }

    @Test
    void it_should_not_allow_initial_keys_with_non_lowercase_alphabet() {
        try {
            Decrypt.decrypt("test 123".getBytes(), 10, "INVALID");
            fail();
        } catch (final InvalidParameterException e) {
            return;
        }
        fail();
    }

    @Test
    void it_should_check_every_possible_keys() {
        AtomicLong count = new AtomicLong();

        Decrypt.getPotentialKeys(0, "asdf".getBytes(), (key) -> {
            count.getAndIncrement();
            return false;
        });

        // The last byte can be whatever while it's a valid one
        assertEquals((int) Math.pow(26, 3), count.intValue());
    }

    @Test
    void it_should_decrypt() {
        final String initial = "\n" +
                "\n" +
                "Wiki Loves Monuments : photographiez un monument historique, aidez Wikipédia et gagnez ! En apprendre plus\n" +
                "Mandoline\n" +
                "Sauter à la navigation\n" +
                "Sauter à la recherche\n" +
                "Page d'aide sur l'homonymie Pour les articles homonymes, voir Mandoline (homonymie).\n" +
                "Si ce bandeau n'est plus pertinent, retirez-le. Cliquez ici pour en savoir plus sur les bandeaux.\n" +
                "Certaines informations figurant dans cet article ou cette section devraient être mieux reliées aux sources mentionnées dans les sections « Bibliographie », « Sources » ou « Liens externes » (avril 2016).\n" +
                "\n" +
                "Améliorez sa vérifiabilité en les associant par des références à l'aide d'appels de notes.\n" +
                "Mandoline napolitaine.\n" +
                "Mandoline Gibson de 1921, modèle A4.\n" +
                "\n" +
                "La mandoline est un instrument de musique à cordes pincées originaire d'Italie. Petit luth à manche court utilisé en musique classique, folklorique ou traditionnelle, elle est surtout répandue dans les pays méditerranéens. Elle est connue, par exemple, comme l'instrument d'accompagnement privilégié de la chanson napolitaine.\n" +
                "Sommaire\n" +
                "\n" +
                "    1 Lutherie\n" +
                "    2 Jeu\n" +
                "    3 Le répertoire\n" +
                "    4 L'évolution de la pratique\n" +
                "    5 Les mandolinistes\n" +
                "    6 Festival international de Lunel\n" +
                "    7 Bibliographie\n" +
                "    8 Références\n" +
                "    9 Voir aussi\n" +
                "        9.1 Articles connexes\n" +
                "        9.2 Liens externes\n" +
                "\n" +
                "Lutherie\n" +
                "\n" +
                "Longue de 70 à 75 cm, la mandoline comporte une caisse de résonance bombée en lamellé-collé, une table d'harmonie avec une grande ouïe centrale ovale, ainsi qu'un manche court, étroit et muni de frettes, se terminant par un chevillier qui sert à accrocher les cordes.\n" +
                "\n" +
                "On distingue deux grands types de mandolines :\n" +
                "\n" +
                "    la mandoline milanaise ou mandurina, à six cordes doubles — le plus souvent en boyau, jouées avec les doigts, ou en acier, jouées avec un plectre — accordées à l'unisson comme suit : sol2, si2, mi3, la3, ré4, sol4. L'instrument actuel s'accorde toutefois souvent comme la guitare. Elle dérive directement des instruments appelés quintern par Sebastian Virdung dans son Musica getutscht (1511) et pandurina par Michael Praetorius dans son Syntagma musicum (1619). Sa caisse de résonance est en forme d'amande, son dos bombé est composé de côtes en bois dur, et sa table d'harmonie comporte une ouïe circulaire ;\n" +
                "    la mandoline napolitaine ou mandolino est apparue dès le XVe siècle, comme l'attestent quelques documents iconographiques. La première source écrite mentionnant cet instrument, due à Francesco Redi, date de 1685. D'après Richard Campbell, il existerait encore 3 exemplaires de mandoline napolitaine, datant respectivement de 1609, 1655 et 1660. C'est actuellement la mandoline la plus répandue, sa caisse adopte la forme d'une larme, et son dos est également bombé, comme celui du luth. Elle s'éloigne cependant de ce dernier, notamment par sa table d'harmonie percée d'une ouïe ovale — à côté de laquelle est incrustée une plaque d'écaille ou de bois dur évitant ainsi à l'instrument d'être endommagé par les coups de plectre — et par son dos encore plus bombé. Elle est munie de quatre cordes doubles en acier accordées en quintes (comme le violon) : soit sol, ré, la et mi (G D A E), du grave vers l'aigu.\n" +
                "\n" +
                "1920 Gibson F-4 mandoline\n" +
                "1917 Gibson H-2 mandole\n" +
                "1924 Gibson K-4 mandoloncelle\n" +
                "1929 Gibson mando-basse\n" +
                "\n" +
                "À partir du XVIIIe siècle, les mandolines forment une famille d'instruments comportant, de l'aigu vers le grave :\n" +
                "\n" +
                "    la soprano ;\n" +
                "    l'alto (accordé do, sol, ré, la du grave vers l'aigu) ;\n" +
                "    le ténor (mandole, accordé une octave au-dessous de la soprano) ;\n" +
                "    la basse ou le mandoloncelle (accordé comme un violoncelle, à l'octave grave de l'alto) ainsi que le mandolone (contrebasse, également appelée archimandola), souvent muni de 7 ou 8 cordes doubles — sauf la chanterelle, simple — accordées fa (ou sol), la, ré, sol, si, mi, la.\n" +
                "\n" +
                "Pour les anglo-saxons, la mandole (mandola) correspond à notre alto (do, sol, ré, la). L'instrument accordé une octave au-dessous de la mandoline (sol, ré la, mi) est généralement nommé, en toute logique, octave mandolin, mais aussi parfois octave mandola, ce qui prête à confusion ! Mandoloncelle se traduit par mandocello. La mandobass ne possède que quatre cordes accordées en quartes (mi, la, ré, sol). Rien ne la distingue donc fondamentalement d'une basse acoustique, si ce n'est qu'elle est généralement jouée debout, à la manière d'une contrebasse.\n" +
                "\n" +
                "Par ailleurs, d'autres types, plus régionaux, de mandoline se sont développés :\n" +
                "\n" +
                "    la mandoline génoise dérive directement de la mandoline milanaise dont elle ne se distingue que par son manche plus étroit et par ses cordes simples, au nombre de 5 ou 6 ;\n" +
                "    la mandoline florentine au corps plus petit mais au manche plus long que la mandoline napolitaine, peut comporter soit 5 doubles cordes (ré, sol, do, mi, la, du grave vers l'aigu), soit 4 cordes simples (accord actuel, comme le violon).\n" +
                "\n" +
                "D'autres types de mandoline sont apparus au fil du temps, tels :\n" +
                "\n" +
                "    la mandoline bluegrass, qui, à part ses quatre doubles cordes, n’a plus grand-chose en commun avec la mandoline baroque, grâce au travail qu’effectua Orville Gibson dès 1898. La nouvelle mandoline présente un dos légèrement arqué (plus du tout bombé), une table d’harmonie également arquée à la forme différente (forme de poire puis asymétrique), des ouïes en forme de f remplaçant la rosace, un manche un peu plus long, un chevalet réglable, etc.\n" +
                "    le banjoline, dans lequel la table d'harmonie en bois fait place à celle d'un banjo, c'est-à-dire munie d'une peau tendue ;\n" +
                "    la mandoline irlandaise, qui adopte presque toutes les caractéristiques de la mandoline napolitaine sauf en ce qui concerne son fond, plat, son manche, un peu plus large, et sa taille (un peu plus grande) ; les deux luthiers les plus connus sont le Dublinois Joe Foley, et Stephan Sobell, installé en Angleterre ;\n" +
                "    la bandolim brésilienne, qui se reconnaît à sa table d'harmonie pratiquement circulaire, percée d'une rosace, et dont le fond est plat.\n" +
                "\n" +
                "Par ailleurs, un certain nombre d'instruments hybrides ont vu le jour :\n" +
                "\n" +
                "    la mandoline sicilienne à 5 voire 6 chœurs (10 voire 12 cordes); la mandoline à quatre chœurs de trois cordes est typiquement utilisée du début à la fin du XIXe siècle pour l'accompagnement d'œuvres vocales ;\n" +
                "    la mandoline électrique (pas de caisse de résonance mais des micros) ;\n" +
                "    la mandriola (cet instrument comporte des triples cordes, et non des doubles cordes : sol-sol-sol ré-ré-ré la-la-la mi-mi-mi) ;\n" +
                "    plusieurs luthiers contemporains fabriquent des mandolines dont le corps adopte des formes de plus en plus éloignées de celle des mandolines baroques ou irlandaises. On peut noter les créations d'André Sakellaridès, luthier à Marseille, qui a créé une famille de mandolines modernes, à fond plat, asymétriques.\n" +
                "\n" +
                "Enfin, elle a été adoptée au début du XXe siècle dans la musique arabo-andalouse algérienne, pour l'interprétation de la nouba et de chaâbi algérien, sous le nom de snitra. La mandole algérienne qui en est dérivée a elle un long manche et une caisse plate.\n" +
                "Jeu\n" +
                "Article connexe : Plectre.\n" +
                "\n" +
                "Tenu entre le pouce et l'index (ou l'index plus le majeur), le plectre — souvent appelé « médiator » en France, ou « pic » au Québec et en Amérique francophone — est l'objet avec lequel on pince les cordes de la mandoline en staccato (notes détachées) ou en tremolos pour produire une sonorité continue ou des passages legato, jeu utilisé surtout à partir de la fin du XIXe siècle. Le plectre a été fabriqué dans différentes matières au cours des siècles : os, plume, ivoire, écaille… Sa forme évolue avec la matière utilisée pour les cordes ainsi que les modifications de la forme de l'instrument :\n" +
                "\n" +
                "    en musique baroque, la « plume » (de corbeau ou d'autruche) est utilisée avec les cordes en boyau dont les graves sont filées d'un brin de soie ;\n" +
                "    le plectre de forme ovoïde, en tortue ou en ivoire, est utilisée dès l'apparition des cordes métalliques (on utilisait alors des cordes de clavecin parfois vrillées pour les graves) ;\n" +
                "    le plectre en os, en ivoire ou en pierre polie, plus rigide, est une invention américaine (et ne fait en aucun cas partie de la tradition italienne)[réf. nécessaire] pour la mandoline napolitaine ; sa forme devient alors moins allongée et l'extrémité moins pointue.\n" +
                "\n" +
                "Le répertoire\n" +
                "Article connexe : Liste de compositeurs ayant composé pour la mandoline.\n" +
                "Joueuse de mandoline. Peinture de Julie Wilhelmine Hagen-Schwarz (1851)\n" +
                "\n" +
                "La mandoline est très vite devenue un instrument populaire car sa facilité de jeu l'emportait sur le luth, de même que son coût.\n" +
                "\n" +
                "Les premiers exemples connus de pièces musicales pour mandoline remontent aux environs de 1700 ; disposés en tablature (et non comme une partition), écrits pour la mandoline milanaise, ils sont dus à Francesco Contini (Sonate al mandolino solo) et Filippo Sauli (un manuscrit entier, conservé dans une bibliothèque tchèque). D'après Richard Campbell, Fétis prétendit par ailleurs qu'un certain Johann Strohbach aurait composé des concertos pour mandoline avant 1700.\n" +
                "\n" +
                "Ensuite, bon nombre de compositeurs — surtout des Italiens — composèrent des pièces pour mandoline dans le courant du XVIIIe siècle.\n" +
                "\n" +
                "Les premières méthodes datent respectivement de 1765 (Méthode raisonnée pour passer du violon à la mandoline de Gabriele Leone), 1768 (Méthode pour apprendre à jouer de la mandoline sans maître de Pierre ou Pietro Denis), 1770 (Giovanni Fouchetti, qui publia à Paris sa Méthode pour apprendre facilement à jouer de la mandoline à 4 et à 6 cordes) et 1772 (Michel Corrette) pour la France, et 1805 (Anweisung, die Mandoline von selbst zu erlernen, publiée à Leipzig par Bortolazzi) pour l'Allemagne ; deux autres méthodes, en anglais et en français, furent publiées avant 1805. Si ces méthodes ont été rédigées, à deux exceptions, par des Italiens, aucune méthode de mandoline n'a été retrouvée en Italie avant le début du XIXe siècle.\n" +
                "\n" +
                "Le répertoire instrumental original — sans tenir compte des nombreuses transcriptions et autres arrangements — pour mandoline ne se distingue ni en quantité ni en qualité, car il ne contient aucune réelle grande œuvre due à un compositeur de tout premier plan.\n" +
                "\n" +
                "En effet, à part 6 pages intéressantes, à savoir les deux incontournables concertos de Vivaldi (pour une mandoline, cordes & basse continue en ut majeur, RV 425 ; pour 2 mandolines, cordes & basse continue, en sol majeur, RV 532) et 4 petites pièces de Beethoven datant de 1796 (Sonatine WoO 43a ; Adagio ma non troppo WoO 43b ; Sonatine WoO 44a ; Andante con Variazioni WoO 44b), la mandoline a été quasi ignorée de tous les grands compositeurs.\n" +
                "\n" +
                "La mandoline est cependant présente dans un oratorio de Haendel en 1748.\n" +
                "\n" +
                "La mandoline a également fait son apparition à l'opéra dès le début du XVIIIe siècle, dans La conquista delle Spagne di Scipione Africano il giovane (1707) de Bononcini, puis dans plusieurs autres œuvres lyriques (de Naumann, Arne, Grétry, Mozart…). La sérénade de Don Juan avec accompagnement de mandoline dans l’opéra Don Juan de Mozart est une page célèbre1.\n" +
                "\n" +
                "La mandoline fut introduite dans l’orchestre symphonique au début du XXe siècle par Mahler (7e et 8e Symphonies), Schoenberg (Variations op. 31), Stravinski (Agon), Prokofiev (Roméo et Juliette), Webern (Pièces opus 10), etc.\n" +
                "\n" +
                "Il a ensuite fallu attendre la fin du XIXe siècle pour que l’opéra s’intéresse à nouveau à la mandoline, grâce à Verdi (Otello, 1887), Pfitzner (Palestrina, 1912-15), Henze (König Hirsch, 1956), etc. Des compositeurs comme Schoenberg, Goffredo Petrassi, Manoury et Bruno Giner ont aussi utilisé la mandoline en musique de chambre.\n" +
                "\n" +
                "Le chef d'orchestre Vittorio Monti composa à la même époque sa célèbre Csardas pour mandoline ou violon.\n" +
                "\n" +
                "Pierre Boulez a également intégré la mandoline dans ses compositions, notamment « Improvisation 3 » de 1960 pour soprano, mandoline et célesta2, Eclat3, Don4.\n" +
                "\n" +
                "Par ailleurs, une littérature très abondante de pièces pour mandoline et ensembles à plectres, duos, Estudiantina formation en quatuor composé de 2 mandolines, d'une mandole et d'une guitare, jusqu'au grand orchestre, a fleuri au cours de l’âge d’or de la mandoline de la fin du XIXe siècle et de la première moitié du XXe siècle5. Ces pièces de style romantique, néoclassique, adaptations de folklore européen, de chansons à la mode, la plupart d’exécution assez facile, étaient très populaires. De très nombreux arrangements d’œuvres classiques étaient également proposés aux amateurs. Les compositions de Mario Maciocchi ou celles plus tardives de Sylvain Dagosto sont représentatives de cette musique récréative.\n" +
                "\n" +
                "L’accord identique des 2 instruments permet d’interpréter à la mandoline la plupart des compositions pour violon, ainsi la célèbre chaconne de la Partita no 2 BWV 1004 pour violon seul de Jean-Sébastien Bach ou la partita no 3 BWV 10066,7.\n" +
                "\n" +
                "La partie de violon des œuvres de Paganini avec accompagnement de guitare est également parfois jouée à la mandoline8\n" +
                "L'évolution de la pratique\n" +
                "Esudiantina Espagnola Paris 1878\n" +
                "64 membres de l'Estudiantina Espagnola au Carnaval de Paris le 16 mars 18789.\n" +
                "Spanish Students at Carnival or Mardi Gras 1878\n" +
                "La foule parisienne entourant l'Estudiantina Espanola jouant des airs espagnols dans le Jardin des Tuileries pendant le Mardi Gras 6 mars 1878. La foule était évaluée à 56,000 près du Café Riche10.\n" +
                "\n" +
                "En dehors de l’Italie et de l’Espagne où l’instrument est resté présent depuis son apparition, la pratique de la mandoline a connu de très fortes fluctuations.\n" +
                "\n" +
                "Après l'engouement de la fin du XVIIIe siècle attesté par la publication de méthodes, par la sérénade de l‘opéra Don Juan de Mozart, par les pièces composées par le jeune Beethoven, la pratique de la mandoline déclina jusqu'à une quasi-disparition en dehors de l'Italie au milieu du XIXe siècle ce que constate Berlioz dans son traité d'instrumentation11.\n" +
                "\n" +
                "Après cette éclipse, la mandoline connut son âge d'or à partir de 1880.\n" +
                "\n" +
                "La renaissance fait suite au succès d'ensembles de mandolinistes étudiants espagnols à l’exposition universelle de Paris de 1878 d’où le nom d’Estudiantina fréquemment donné en France aux ensembles à plectre12.\n" +
                "\n" +
                "L'estudiantina espagnola défila en costumes historiques au Carnaval de Paris du 2 au 15 mars. Cet ensemble comprenant également des flûtistes, de guitaristes et des violonistes était accompagné de tambourins. Il donna des concerts nocturnes auxquels auraient assisté 10 000 spectateurs et même 56 000 spectateurs d'après un inspecteur de police chargé d'assurer la sécurité12.\n" +
                "\n" +
                "De 1880 aux années 1920 la mandoline jouée seule, en formation réduite (duos mandoline et guitare, quatuor de 2 mandolines, 1 mandole et guitare) ou en ensemble orchestral fut extrêmement populaire en Europe, en Amérique et au Japon13\n" +
                "\n" +
                "Après cet âge d’or, sa pratique a rapidement décliné concurrencée par le jazz et par la vogue de l'accordéon des années 1930 aux années 1950.\n" +
                "\n" +
                "Cette baisse n’a pas entraîné une disparition aussi complète que celle du milieu du XIXe siècle mais son image était dans les années 1950 et encore dans les décennies suivantes celle d’un instrument désuet et déprécié notamment évoquée par la chanson la mandoline de Bourvil.\n" +
                "\n" +
                "À partir du milieu du XXe siècle la mandoline est utilisée dans d’autres styles : bluegrass, jazz, rock, musique celtique.\n" +
                "\n" +
                "Sa pratique en formation classique d’orchestre à plectre est restée très vivace en Allemagne et au Japon.\n" +
                "Les mandolinistes\n" +
                "Article détaillé : Liste de mandolinistes.\n" +
                "Festival international de Lunel\n" +
                "\n" +
                "De 2004 à 2014, la ville de Lunel, près de Montpellier, a accueilli un festival annuel de mandolines. Il témoigne du renouveau de cet instrument alors qu'« à la fin du XXe siècle, la mandoline n'était en Europe que l'instrument de quelques obstinés isolés… petit instrument fragile et désuet, qui n'avait pu faire face aux grands chambardements culturels de l'époque contemporaine »14. Le 9e festival (31 octobre-3 novembre 2012) a été ouvert également à quelques instruments dits 'cousins' (bouzouki, domra, guitare portugaise, saz, tiple.)\n" +
                "\n" +
                "La dixième année du festival a été célébrée avec la présence de musiciens renommés. Le parrain pour cette décennie du festival était John Paul Jones, cofondateur avec Jimmy Page du groupe de rock Led Zeppelin (1968-1980), compositeur, arrangeur, bassiste et claviériste du groupe mythique anglais. Ce multi-instrumentiste joue également de la mandoline à trois manches. Hamilton de Holanda joueur virtuose brésilien(jazz, choro) participait aussi à cette dixième manifestation avec le chanteur français Féloche et le virtuose musicien israélien Avi Avital.\n" +
                "\n" +
                "Le festival a d’autre part invité de nombreux mandolinistes renommés tels que Mike Marshall, Chris Thile ou Carlo Aonzo.\n" +
                "Bibliographie\n" +
                "\n" +
                "    Marc Honegger, Dictionnaire de la musique : technique, formes, instruments, Éditions Bordas, coll. « Science de la Musique », 1976, 1109 p. [détail des éditions] (ISBN 2-04-005140-6)\n" +
                "    Wölki, K., Die Geschichte der Mandoline, Berlin, 1939, rev. 2/1974\n" +
                "    Tyler, J. & Sparks, P., The Early Mandolin, Oxford University Press, 1992, 0-19-816302-9\n" +
                "    Sparks, P., The Classical mandolin, Oxford University Press, 1995, (ISBN 0-19-816295-2)\n" +
                "    Sparks, Paul, An introduction to the eighteenth Century Repertoire of the Neapolitan Mandoline, Plucked String, 1999, (ISBN 0-9614120-5-4)\n" +
                "    Ranieri, Silvio, La Mandoline, Encyclopédie de la musique et dictionnaire de conservatoire, Delagrave, cop. 1925\n" +
                "    de Saint-Foix, G., « Un fonds inconnu de compositions pour mandoline », dans Revue de Musicologie, XIV, 1933, p. 129\n" +
                "    Bone, Ph. J., The Guitar and Mandolin. Biographies of Celebrated Players and Composers for these Instruments, Londres, 1914 (2 rééditions, en 1954 et 1972)\n" +
                "    Troughton, John, The Mandolin Manual \"The art, craft and science of the Mandolin and Mandola\", The Crowood Press, 2002, (ISBN 978-1-86126-496-1)\n" +
                "\n" +
                "Sur les autres projets Wikimedia :\n" +
                "\n" +
                "    Mandoline, sur Wikimedia Commons \n" +
                "\n" +
                "Mémoire universitaire non publié :\n" +
                "\n" +
                "    Buisson, Cédric, L'Estudiantina de Roanne Contribution à l'étude des orchestres à plectre, mémoire de maîtrise, 2003.\n" +
                "    Zernecke, Ariane, Die Mandoline in der DDR - eine Bestandsaufahme, 2002. (Lien pdf [archive])\n" +
                "\n" +
                "Références\n" +
                "\n" +
                "https://fr.wikisource.org/wiki/Page:Berlioz_-_Trait%C3%A9_d%E2%80%99instrumentation_et_d%E2%80%99orchestration.djvu/95 [archive]\n" +
                "http://www.ensemble2e2m.com/fr/page-18/search/boulez?id_compo=15 [archive]\n" +
                "http://brahms.ircam.fr/works/work/6968/ [archive]\n" +
                "http://brahms.ircam.fr/works/work/6966 [archive]\n" +
                "http://www.journalzibeline.fr/critique/une-histoire-marseillaise/ [archive]\n" +
                "https://www.youtube.com/watch?v=yRn29Xc-mV4 [archive]\n" +
                "https://www.youtube.com/watch?v=NL8XhxMLqbs [archive]\n" +
                "https://www.youtube.com/watch?v=KePkIagPEH8 [archive]\n" +
                "(en) Félix O. Martín Sárraga, « Crónica del viaje de la Estudiantina Española al Carnaval de París de 1878 según la prensa de la época. », academia.edu, vol. 2,\u200E 2016, p. 7, 11, 44 (lire en ligne [archive]) :\n" +
                "\n" +
                "    « (p.7) El de la estudiantina española, compuesta por 64 personas y que está en París, es muy bello y gusta mucho... (p.11) A las nueve los 64 jóvenes que forman la estudiantina llegaron a nuestra casa atravesando con gran dificultad por enmedio del público reunido delante de nuestro hotel... (p.44) La estudiantina se compone de 64 jóvenes que, según las noticias más fidedignas, y desnudas [de] algún tanto de la exageración francesa que los ha ennoblecido con antiguos titulos, por lo menos de hidalguía, proceden en gran parte del Conservatorio y de la Facultad de Medicina, que fue siempre la que dió más estudiantes a su Tuna. [Translation]:(p.7) L’estudiantina se compose de 64 jeunes musiciens qui viennent en grande partie du Conservatoire et de l'école de médecine, qui a toujours été celui qui a donné plus d'étudiants à leur groupe.T »\n" +
                "\n" +
                "(en) Félix O. Martín Sárraga, « Crónica del viaje de la Estudiantina Española al Carnaval de París de 1878 según la prensa de la época. », academia.edu, vol. 2,\u200E 2016, p. 15–16 (lire en ligne [archive]) :\n" +
                "\n" +
                "    « A las tres el café Riche era el centro de una aglomeración de gentes de que nada puede dar idea puesto que el inspector de policía especialmente encargado de proteger a la estudiantina la ha evaluado en su parte oficial en 56.000 personas.] »\n" +
                "\n" +
                "https://fr.wikisource.org/wiki/Page%3ABerlioz_-_Trait%C3%A9_d%E2%80%99instrumentation_et_d%E2%80%99orchestration.djvu/96 [archive]\n" +
                "(en) Félix O. Martín Sárraga, « Crónica del viaje de la Estudiantina Española al Carnaval de París de 1878 según la prensa de la época. », academia.edu, vol. 2,\u200E 2016, p. 7, 8, 14, 16, 39, 44, 46, 47 (lire en ligne [archive])\n" +
                "http://comiteest.canalblog.com/archives/2010/04/20/17625382.html [archive]\n" +
                "\n" +
                "    Selon Olivier Chabrol, directeur artistique du Festival international de Lunel, cité par Alexis Ferenczi, Le HuffPos, 1er novembre 2012, https://www.huffingtonpost.fr/2012/10/31/festival-international-mandolines-lunel-instrument_n_2050789.html [archive]\n" +
                "\n" +
                "Voir aussi\n" +
                "Articles connexes\n" +
                "\n" +
                "    Banjoline\n" +
                "    Cistre\n" +
                "    Guitare portugaise\n" +
                "    Mandoline country\n" +
                "    Mandoloncelle\n" +
                "    Mandolute\n" +
                "    Orchestre à plectre\n" +
                "    Tuna\n" +
                "    Mandole algérien\n" +
                "\n" +
                "Liens externes\n" +
                " [afficher]\n" +
                "v · m\n" +
                "Listes de mandolinistes\n" +
                " [afficher]\n" +
                "v · m\n" +
                "Instruments de la musique classique\n" +
                "\n" +
                "    Portail des musiques du monde Portail de la musique classique Portail de l’Italie \n" +
                "\n" +
                "Catégories :\n" +
                "\n" +
                "    MandolineLuth (organologie)Instrument de musique classiqueInstrument de la musique italienneInstrument de la musique indienneInstrument de la musique irlandaiseInstrument de la musique suisseInstrument de musique folk\n" +
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
                "Dans d’autres projets\n" +
                "\n" +
                "    Wikimedia Commons\n" +
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
                "    Corsu\n" +
                "    Deutsch\n" +
                "    English\n" +
                "    Español\n" +
                "    Italiano\n" +
                "    日本語\n" +
                "    Occitan\n" +
                "    Português\n" +
                "\n" +
                "Modifier les liens\n" +
                "\n" +
                "    La dernière modification de cette page a été faite le 31 juillet 2019 à 23:15.\n" +
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
        final String key = "aaaa";
        final IEncryption encryption = new XorEncryption(key.getBytes());
        final byte[] encoded = encryption.encrypt(initial.getBytes());

        final byte[] decoded = Decrypt.decrypt(encoded, 4);

        assertEquals(initial.getBytes(), decoded);
    }
}
