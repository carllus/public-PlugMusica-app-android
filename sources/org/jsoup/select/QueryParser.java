package org.jsoup.select;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.parser.TokenQueue;
import org.jsoup.select.CombiningEvaluator;
import org.jsoup.select.Evaluator;
import org.jsoup.select.Selector;
import org.jsoup.select.StructuralEvaluator;

class QueryParser {
    private static final String[] AttributeEvals = {"=", "!=", "^=", "$=", "*=", "~="};
    private static final Pattern NTH_AB = Pattern.compile("((\\+|-)?(\\d+)?)n(\\s*(\\+|-)?\\s*\\d+)?", 2);
    private static final Pattern NTH_B = Pattern.compile("(\\+|-)?(\\d+)");
    private static final String[] combinators = {",", ">", "+", "~", " "};
    private List<Evaluator> evals = new ArrayList();
    private String query;

    /* renamed from: tq */
    private TokenQueue f946tq;

    private QueryParser(String query2) {
        this.query = query2;
        this.f946tq = new TokenQueue(query2);
    }

    public static Evaluator parse(String query2) {
        return new QueryParser(query2).parse();
    }

    /* access modifiers changed from: package-private */
    public Evaluator parse() {
        this.f946tq.consumeWhitespace();
        if (this.f946tq.matchesAny(combinators)) {
            this.evals.add(new StructuralEvaluator.Root());
            combinator(this.f946tq.consume());
        } else {
            findElements();
        }
        while (!this.f946tq.isEmpty()) {
            boolean seenWhite = this.f946tq.consumeWhitespace();
            if (this.f946tq.matchesAny(combinators)) {
                combinator(this.f946tq.consume());
            } else if (seenWhite) {
                combinator(' ');
            } else {
                findElements();
            }
        }
        if (this.evals.size() == 1) {
            return this.evals.get(0);
        }
        return new CombiningEvaluator.And((Collection<Evaluator>) this.evals);
    }

    private void combinator(char combinator) {
        Evaluator currentEval;
        Evaluator rootEval;
        CombiningEvaluator.C1061Or or;
        Evaluator currentEval2;
        this.f946tq.consumeWhitespace();
        Evaluator newEval = parse(consumeSubQuery());
        boolean replaceRightMost = false;
        if (this.evals.size() == 1) {
            currentEval = this.evals.get(0);
            rootEval = currentEval;
            if ((rootEval instanceof CombiningEvaluator.C1061Or) && combinator != ',') {
                currentEval = ((CombiningEvaluator.C1061Or) currentEval).rightMostEvaluator();
                replaceRightMost = true;
            }
        } else {
            currentEval = new CombiningEvaluator.And((Collection<Evaluator>) this.evals);
            rootEval = currentEval;
        }
        this.evals.clear();
        if (combinator == '>') {
            currentEval2 = new CombiningEvaluator.And(newEval, new StructuralEvaluator.ImmediateParent(currentEval));
        } else if (combinator == ' ') {
            currentEval2 = new CombiningEvaluator.And(newEval, new StructuralEvaluator.Parent(currentEval));
        } else if (combinator == '+') {
            currentEval2 = new CombiningEvaluator.And(newEval, new StructuralEvaluator.ImmediatePreviousSibling(currentEval));
        } else if (combinator == '~') {
            currentEval2 = new CombiningEvaluator.And(newEval, new StructuralEvaluator.PreviousSibling(currentEval));
        } else if (combinator == ',') {
            if (currentEval instanceof CombiningEvaluator.C1061Or) {
                or = (CombiningEvaluator.C1061Or) currentEval;
                or.add(newEval);
            } else {
                or = new CombiningEvaluator.C1061Or();
                or.add(currentEval);
                or.add(newEval);
            }
            currentEval2 = or;
        } else {
            throw new Selector.SelectorParseException("Unknown combinator: " + combinator, new Object[0]);
        }
        if (replaceRightMost) {
            ((CombiningEvaluator.C1061Or) rootEval).replaceRightMostEvaluator(currentEval2);
        } else {
            rootEval = currentEval2;
        }
        this.evals.add(rootEval);
    }

    private String consumeSubQuery() {
        StringBuilder sq = new StringBuilder();
        while (!this.f946tq.isEmpty()) {
            if (this.f946tq.matches("(")) {
                sq.append("(").append(this.f946tq.chompBalanced('(', ')')).append(")");
            } else if (this.f946tq.matches("[")) {
                sq.append("[").append(this.f946tq.chompBalanced('[', ']')).append("]");
            } else if (this.f946tq.matchesAny(combinators)) {
                break;
            } else {
                sq.append(this.f946tq.consume());
            }
        }
        return sq.toString();
    }

    private void findElements() {
        if (this.f946tq.matchChomp("#")) {
            byId();
        } else if (this.f946tq.matchChomp(".")) {
            byClass();
        } else if (this.f946tq.matchesWord()) {
            byTag();
        } else if (this.f946tq.matches("[")) {
            byAttribute();
        } else if (this.f946tq.matchChomp("*")) {
            allElements();
        } else if (this.f946tq.matchChomp(":lt(")) {
            indexLessThan();
        } else if (this.f946tq.matchChomp(":gt(")) {
            indexGreaterThan();
        } else if (this.f946tq.matchChomp(":eq(")) {
            indexEquals();
        } else if (this.f946tq.matches(":has(")) {
            has();
        } else if (this.f946tq.matches(":contains(")) {
            contains(false);
        } else if (this.f946tq.matches(":containsOwn(")) {
            contains(true);
        } else if (this.f946tq.matches(":matches(")) {
            matches(false);
        } else if (this.f946tq.matches(":matchesOwn(")) {
            matches(true);
        } else if (this.f946tq.matches(":not(")) {
            not();
        } else if (this.f946tq.matchChomp(":nth-child(")) {
            cssNthChild(false, false);
        } else if (this.f946tq.matchChomp(":nth-last-child(")) {
            cssNthChild(true, false);
        } else if (this.f946tq.matchChomp(":nth-of-type(")) {
            cssNthChild(false, true);
        } else if (this.f946tq.matchChomp(":nth-last-of-type(")) {
            cssNthChild(true, true);
        } else if (this.f946tq.matchChomp(":first-child")) {
            this.evals.add(new Evaluator.IsFirstChild());
        } else if (this.f946tq.matchChomp(":last-child")) {
            this.evals.add(new Evaluator.IsLastChild());
        } else if (this.f946tq.matchChomp(":first-of-type")) {
            this.evals.add(new Evaluator.IsFirstOfType());
        } else if (this.f946tq.matchChomp(":last-of-type")) {
            this.evals.add(new Evaluator.IsLastOfType());
        } else if (this.f946tq.matchChomp(":only-child")) {
            this.evals.add(new Evaluator.IsOnlyChild());
        } else if (this.f946tq.matchChomp(":only-of-type")) {
            this.evals.add(new Evaluator.IsOnlyOfType());
        } else if (this.f946tq.matchChomp(":empty")) {
            this.evals.add(new Evaluator.IsEmpty());
        } else if (this.f946tq.matchChomp(":root")) {
            this.evals.add(new Evaluator.IsRoot());
        } else {
            throw new Selector.SelectorParseException("Could not parse query '%s': unexpected token at '%s'", this.query, this.f946tq.remainder());
        }
    }

    private void byId() {
        String id = this.f946tq.consumeCssIdentifier();
        Validate.notEmpty(id);
        this.evals.add(new Evaluator.C1062Id(id));
    }

    private void byClass() {
        String className = this.f946tq.consumeCssIdentifier();
        Validate.notEmpty(className);
        this.evals.add(new Evaluator.Class(className.trim().toLowerCase()));
    }

    private void byTag() {
        String tagName = this.f946tq.consumeElementSelector();
        Validate.notEmpty(tagName);
        if (tagName.contains("|")) {
            tagName = tagName.replace("|", ":");
        }
        this.evals.add(new Evaluator.Tag(tagName.trim().toLowerCase()));
    }

    private void byAttribute() {
        TokenQueue cq = new TokenQueue(this.f946tq.chompBalanced('[', ']'));
        String key = cq.consumeToAny(AttributeEvals);
        Validate.notEmpty(key);
        cq.consumeWhitespace();
        if (cq.isEmpty()) {
            if (key.startsWith("^")) {
                this.evals.add(new Evaluator.AttributeStarting(key.substring(1)));
            } else {
                this.evals.add(new Evaluator.Attribute(key));
            }
        } else if (cq.matchChomp("=")) {
            this.evals.add(new Evaluator.AttributeWithValue(key, cq.remainder()));
        } else if (cq.matchChomp("!=")) {
            this.evals.add(new Evaluator.AttributeWithValueNot(key, cq.remainder()));
        } else if (cq.matchChomp("^=")) {
            this.evals.add(new Evaluator.AttributeWithValueStarting(key, cq.remainder()));
        } else if (cq.matchChomp("$=")) {
            this.evals.add(new Evaluator.AttributeWithValueEnding(key, cq.remainder()));
        } else if (cq.matchChomp("*=")) {
            this.evals.add(new Evaluator.AttributeWithValueContaining(key, cq.remainder()));
        } else if (cq.matchChomp("~=")) {
            this.evals.add(new Evaluator.AttributeWithValueMatching(key, Pattern.compile(cq.remainder())));
        } else {
            throw new Selector.SelectorParseException("Could not parse attribute query '%s': unexpected token at '%s'", this.query, cq.remainder());
        }
    }

    private void allElements() {
        this.evals.add(new Evaluator.AllElements());
    }

    private void indexLessThan() {
        this.evals.add(new Evaluator.IndexLessThan(consumeIndex()));
    }

    private void indexGreaterThan() {
        this.evals.add(new Evaluator.IndexGreaterThan(consumeIndex()));
    }

    private void indexEquals() {
        this.evals.add(new Evaluator.IndexEquals(consumeIndex()));
    }

    private void cssNthChild(boolean backwards, boolean ofType) {
        int b = 0;
        int a = 1;
        String argS = this.f946tq.chompTo(")").trim().toLowerCase();
        Matcher mAB = NTH_AB.matcher(argS);
        Matcher mB = NTH_B.matcher(argS);
        if ("odd".equals(argS)) {
            a = 2;
            b = 1;
        } else if ("even".equals(argS)) {
            a = 2;
            b = 0;
        } else if (mAB.matches()) {
            if (mAB.group(3) != null) {
                a = Integer.parseInt(mAB.group(1).replaceFirst("^\\+", ""));
            }
            if (mAB.group(4) != null) {
                b = Integer.parseInt(mAB.group(4).replaceFirst("^\\+", ""));
            }
        } else if (mB.matches()) {
            a = 0;
            b = Integer.parseInt(mB.group().replaceFirst("^\\+", ""));
        } else {
            throw new Selector.SelectorParseException("Could not parse nth-index '%s': unexpected format", argS);
        }
        if (ofType) {
            if (backwards) {
                this.evals.add(new Evaluator.IsNthLastOfType(a, b));
            } else {
                this.evals.add(new Evaluator.IsNthOfType(a, b));
            }
        } else if (backwards) {
            this.evals.add(new Evaluator.IsNthLastChild(a, b));
        } else {
            this.evals.add(new Evaluator.IsNthChild(a, b));
        }
    }

    private int consumeIndex() {
        String indexS = this.f946tq.chompTo(")").trim();
        Validate.isTrue(StringUtil.isNumeric(indexS), "Index must be numeric");
        return Integer.parseInt(indexS);
    }

    private void has() {
        this.f946tq.consume(":has");
        String subQuery = this.f946tq.chompBalanced('(', ')');
        Validate.notEmpty(subQuery, ":has(el) subselect must not be empty");
        this.evals.add(new StructuralEvaluator.Has(parse(subQuery)));
    }

    private void contains(boolean own) {
        this.f946tq.consume(own ? ":containsOwn" : ":contains");
        String searchText = TokenQueue.unescape(this.f946tq.chompBalanced('(', ')'));
        Validate.notEmpty(searchText, ":contains(text) query must not be empty");
        if (own) {
            this.evals.add(new Evaluator.ContainsOwnText(searchText));
        } else {
            this.evals.add(new Evaluator.ContainsText(searchText));
        }
    }

    private void matches(boolean own) {
        this.f946tq.consume(own ? ":matchesOwn" : ":matches");
        String regex = this.f946tq.chompBalanced('(', ')');
        Validate.notEmpty(regex, ":matches(regex) query must not be empty");
        if (own) {
            this.evals.add(new Evaluator.MatchesOwn(Pattern.compile(regex)));
        } else {
            this.evals.add(new Evaluator.Matches(Pattern.compile(regex)));
        }
    }

    private void not() {
        this.f946tq.consume(":not");
        String subQuery = this.f946tq.chompBalanced('(', ')');
        Validate.notEmpty(subQuery, ":not(selector) subselect must not be empty");
        this.evals.add(new StructuralEvaluator.Not(parse(subQuery)));
    }
}
