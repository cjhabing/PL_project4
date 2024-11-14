import java.util.List;
public class ParserImpl extends Parser {

    public ParserImpl() {
        super();
    }

    @Override
    public Expr do_parse() throws Exception {
        return parseterm();
    }

    private Expr parseterm() throws Exception {
    Expr factor = parsefactor();
    if (peek(TokenType.PLUS, 0) || peek(TokenType.MINUS, 0)) {
        Token token = consume(peek(TokenType.PLUS, 0) ? TokenType.PLUS : TokenType.MINUS);
        Expr expression = parseterm();
        return token.ty == TokenType.PLUS ? new PlusExpr(factor, expression) : new MinusExpr(factor, expression);
    }
    return factor;
    }


    private Expr parsefactor() throws Exception {
    Expr literal = parseliteral();
    if (peek(TokenType.TIMES, 0) || peek(TokenType.DIV, 0)) {
    Token token = consume(peek(TokenType.TIMES, 0) ? TokenType.TIMES : TokenType.DIV);
    Expr expression1 = parsefactor();
    return token.ty == TokenType.TIMES ? new TimesExpr(literal, expression1) : new DivExpr(literal, expression1);
    }
    return literal;

    }

    private Expr parseliteral() throws Exception {
        if (peek(TokenType.NUM, 0)) {
    return new FloatExpr(Float.parseFloat(consume(TokenType.NUM).lexeme));
    } else if (peek(TokenType.LPAREN, 0)) {
    consume(TokenType.LPAREN);
    Expr Expression3 = parseterm();
    consume(TokenType.RPAREN);
    return Expression3;
    }
    throw new RuntimeException("Encountered unexpected token: " + peek(TokenType.NUM, 0));
    }
}
