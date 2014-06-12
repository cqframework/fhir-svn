package org.hl7.fhir.utilities.xhtml;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Cell;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

public class HeirarchicalTableGenerator {

  public class Piece {
    private String tag;
    private String reference;
    private String text;
    private String hint;
    
    public Piece(String tag) {
      super();
      this.tag = tag;
    }
    
    public Piece(String reference, String text, String hint) {
      super();
      this.reference = reference;
      this.text = text;
      this.hint = hint;
    }
    public String getReference() {
      return reference;
    }
    public String getText() {
      return text;
    }
    public String getHint() {
      return hint;
    }

    public String getTag() {
      return tag;
    }

  }
  
  public class Cell {
    private List<Piece> pieces = new ArrayList<HeirarchicalTableGenerator.Piece>();

    public Cell() {
      
    }
    public Cell(String prefix, String reference, String text, String hint, String suffix) {
      super();
      if (!Utilities.noString(prefix))
        pieces.add(new Piece(null, prefix, null));
      pieces.add(new Piece(reference, text, hint));
      if (!Utilities.noString(suffix))
        pieces.add(new Piece(null, suffix, null));
    }
    public List<Piece> getPieces() {
      return pieces;
    }
  }

  public class Title extends Cell {
    private int width;

    public Title(String prefix, String reference, String text, String hint, String suffix, int width) {
      super(prefix, reference, text, hint, suffix);
      this.width = width;
    }
  }
  
  public class Row {
    private List<Row> subRows = new ArrayList<HeirarchicalTableGenerator.Row>();
    private List<Cell> cells = new ArrayList<HeirarchicalTableGenerator.Cell>();
    private String icon;
    
    public List<Row> getSubRows() {
      return subRows;
    }
    public List<Cell> getCells() {
      return cells;
    }
    public String getIcon() {
      return icon;
    }
    public void setIcon(String icon) {
      this.icon = icon;
    }
    
  }

  public class TableModel {
    private List<Title> titles = new ArrayList<HeirarchicalTableGenerator.Title>();
    private List<Row> rows = new ArrayList<HeirarchicalTableGenerator.Row>();
    public List<Title> getTitles() {
      return titles;
    }
    public List<Row> getRows() {
      return rows;
    }
  }


  private String dest;
  
  
  public HeirarchicalTableGenerator(String dest) {
    super();
    this.dest = dest;
  }


  public XhtmlNode generate(TableModel model) throws Exception {
    checkModel(model);
    XhtmlNode table = new XhtmlNode(NodeType.Element, "table").setAttribute("border", "0").setAttribute("cellspacing", "0").setAttribute("cellpadding", "0");
    table.setAttribute("class", "heirarchy");
    XhtmlNode tr = table.addTag("tr");
    tr.setAttribute("class", "heirarchy");
    for (Title t : model.getTitles()) {
      XhtmlNode tc = renderCell(tr, t, "th", null, null, false);
      if (t.width != 0)
        tc.setAttribute("style", "width: "+Integer.toString(t.width)+"px");
    }
    for (Row r : model.getRows()) {
      renderRow(table, r, 0, new ArrayList<Boolean>());
    }
    return table;
  }


  private void renderRow(XhtmlNode table, Row r, int indent, List<Boolean> indents) throws Exception {
    XhtmlNode tr = table.addTag("tr");
    tr.setAttribute("class", "heirarchy");
    boolean first = true;
    for (Cell t : r.getCells()) {
      renderCell(tr, t, "td", first ? r.getIcon() : null, first ? indents : null, !r.getSubRows().isEmpty());
      first = false;
    }
    
    for (int i = 0; i < r.getSubRows().size(); i++) {
      Row c = r.getSubRows().get(i);
      List<Boolean> ind = new ArrayList<Boolean>();
      ind.addAll(indents);
      if (i == r.getSubRows().size() - 1)
        ind.add(true);
      else
        ind.add(false);
      renderRow(table, c, indent+1, ind);
    }
  }


  private XhtmlNode renderCell(XhtmlNode tr, Cell c, String name, String icon, List<Boolean> indents, boolean hasChildren) throws Exception {
    XhtmlNode tc = tr.addTag(name);
    tc.setAttribute("class", "heirarchy");
    if (indents != null) {
      tc.addTag("img").setAttribute("src", "tbl_spacer.png").setAttribute("class", "heirarchy");
      tc.setAttribute("style", "white-space: nowrap");
      tc.setAttribute("background", checkExists(indents, hasChildren));
      for (int i = 0; i < indents.size()-1; i++) { 
        if (indents.get(i))
          tc.addTag("img").setAttribute("src", "tbl_blank.png").setAttribute("class", "heirarchy");
        else
          tc.addTag("img").setAttribute("src", "tbl_vline.png").setAttribute("class", "heirarchy");
      }
      if (!indents.isEmpty())
        if (indents.get(indents.size()-1))
          tc.addTag("img").setAttribute("src", "tbl_vjoin_end.png").setAttribute("class", "heirarchy");
        else
          tc.addTag("img").setAttribute("src", "tbl_vjoin.png").setAttribute("class", "heirarchy");
    }
    if (!Utilities.noString(icon)) {
      tc.addTag("img").setAttribute("src", icon).setAttribute("class", "heirarchy");
      tc.addText(" ");
    }
    for (Piece p : c.pieces) {
      if (!Utilities.noString(p.getTag())) {
        tc.addTag(p.getTag());
      } else if (!Utilities.noString(p.getReference())) {
        XhtmlNode a = tc.addTag("a");
        a.setAttribute("href", p.getReference());
        if (!Utilities.noString(p.getHint()))
          a.setAttribute("title", p.getHint());
        a.addText(p.getText());
      } else { 
        if (!Utilities.noString(p.getHint())) {
          XhtmlNode s = tc.addTag("span");
          s.setAttribute("title", p.getHint());
          s.addText(p.getText());
        } else
          tc.addText(p.getText());
      }
    }
    return tc;
  }


  private void checkModel(TableModel model) throws Exception {
    check(!model.getRows().isEmpty(), "Must have rows");
    check(!model.getTitles().isEmpty(), "Must have titles");
    for (Cell c : model.getTitles())
      check(c);
    for (Row r : model.getRows()) 
      check(r, "rows", model.getTitles().size());    
  }


  private void check(Cell c) throws Exception {  
    boolean hasText = false;
    for (Piece p : c.pieces)
      if (!Utilities.noString(p.getText()))
        hasText = true;
    check(hasText, "Title cells must have text");    
  }


  private void check(Row r, String string, int size) throws Exception {
    check(r.getCells().size() == size, "All rows must have the same number of columns as the titles");
    for (Row c : r.getSubRows()) 
      check(c, "rows", size);    
  }


  private String checkExists(List<Boolean> indents, boolean hasChildren) throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("tbl_bck");
    for (Boolean i : indents)
      b.append(i ? "0" : "1");
    if (hasChildren)
      b.append("1");
    else
      b.append("0");
    b.append(".png");
    String file = Utilities.path(dest, b.toString());
    if (!new File(file).exists()) {
      BufferedImage bi = new BufferedImage(400, 2, BufferedImage.TYPE_BYTE_BINARY);
      Graphics2D graphics = bi.createGraphics();
      graphics.setBackground(Color.WHITE);
      graphics.clearRect(0, 0, 400, 2);
      for (int i = 0; i < indents.size(); i++) {
        if (!indents.get(i))
          bi.setRGB(12+(i*16), 0, 0);
      }
      if (hasChildren)
        bi.setRGB(12+(indents.size()*16), 0, 0);
      ImageIO.write(bi, "PNG", new File(file));
    }
    return b.toString();
  }
  
  private void check(boolean check, String message) throws Exception {
    if (!check)
      throw new Exception(message);
  }
}